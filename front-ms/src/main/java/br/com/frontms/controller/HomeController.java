package br.com.frontms.controller;

import br.com.frontms.model.Livro;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import br.com.frontms.model.Autor;

import java.util.Arrays;

@Controller
public class HomeController {

    private final RestClient restClient;

    public HomeController(RestClient restClient) {
        this.restClient = restClient;
    }

    // Página inicial
    @GetMapping("/")
    public String home(Model model) {

        Livro[] livros = restClient.get()
                .uri("http://livro-ms:8081/api/livros")
                .retrieve()
                .body(Livro[].class);

        Autor[] autores = restClient.get()
                .uri("http://autor-ms:8082/api/autores")
                .retrieve()
                .body(Autor[].class);

        model.addAttribute("livros", Arrays.asList(livros));
        model.addAttribute("autores", Arrays.asList(autores));

        return "index";
    }

    // Abre formulário de cadastro
    @GetMapping("/livros/novo")
    public String novoLivro(Model model) {

        Livro livro = new Livro();

        Autor[] autores = restClient.get()
                .uri("http://autor-ms:8082/api/autores")
                .retrieve()
                .body(Autor[].class);

        model.addAttribute("livro", livro);
        model.addAttribute("autores", Arrays.asList(autores));

        return "livro-form";
    }

    // Salva livro
    @PostMapping("/livros/salvar")
    public String salvar(@ModelAttribute Livro livro) {

        restClient.post()
                .uri("http://livro-ms:8081/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .body(livro)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/";
    }

    // Abre formulário de edição
    @GetMapping("/livros/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Livro livro = restClient.get()
                .uri("http://livro-ms:8081/api/livros/{id}", id)
                .retrieve()
                .body(Livro.class);

        Autor[] autores = restClient.get()
                .uri("http://autor-ms:8082/api/autores")
                .retrieve()
                .body(Autor[].class);

        model.addAttribute("livro", livro);
        model.addAttribute("autores", Arrays.asList(autores));

        return "livro-form";
    }
    // Atualiza livro
    @PostMapping("/livros/atualizar")
    public String atualizar(@ModelAttribute Livro livro) {

        restClient.put()
                .uri("http://livro-ms:8081/api/livros/{id}", livro.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(livro)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/";
    }

    // Excluir livro
    @GetMapping("/livros/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        restClient.delete()
                .uri("http://livro-ms:8081/api/livros/{id}", id)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/";
    }
}