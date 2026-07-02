package br.com.autorms.service;

import br.com.autorms.model.Autor;
import br.com.autorms.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> listarTodos() {
        return repository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    public Autor atualizar(Long id, Autor autorAtualizado) {
        Autor autor = buscarPorId(id);

        autor.setNome(autorAtualizado.getNome());
        autor.setNacionalidade(autorAtualizado.getNacionalidade());
        autor.setAnoNascimento(autorAtualizado.getAnoNascimento());

        return repository.save(autor);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}