package br.com.frontms.controller;

import br.com.frontms.model.Autor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

@Controller
public class AutorController {

    private final RestClient restClient;

    public AutorController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/autores")
    public String listar(Model model) {

        Autor[] autores = restClient.get()
                .uri("http://autor-ms:8082/api/autores")
                .retrieve()
                .body(Autor[].class);

        model.addAttribute("autores", Arrays.asList(autores));

        return "autores";
    }

    @GetMapping("/autores/novo")
    public String novo(Model model) {

        model.addAttribute("autor", new Autor());

        return "autor-form";
    }

    @PostMapping("/autores/salvar")
    public String salvar(@ModelAttribute Autor autor) {

        restClient.post()
                .uri("http://autor-ms:8082/api/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .body(autor)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/autores";
    }

    @GetMapping("/autores/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Autor autor = restClient.get()
                .uri("http://autor-ms:8082/api/autores/{id}", id)
                .retrieve()
                .body(Autor.class);

        model.addAttribute("autor", autor);

        return "autor-form";
    }

    @PostMapping("/autores/atualizar")
    public String atualizar(@ModelAttribute Autor autor) {

        restClient.put()
                .uri("http://autor-ms:8082/api/autores/{id}", autor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(autor)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/autores";
    }

    @GetMapping("/autores/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        restClient.delete()
                .uri("http://autor-ms:8082/api/autores/{id}", id)
                .retrieve()
                .toBodilessEntity();

        return "redirect:/autores";
    }
}