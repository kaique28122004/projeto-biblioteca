package br.com.livroms.service;

import br.com.livroms.model.Livro;
import br.com.livroms.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Livro atualizar(Long id, Livro novo) {
        Livro livro = buscarPorId(id);

        livro.setTitulo(novo.getTitulo());
        livro.setGenero(novo.getGenero());
        livro.setAnoPublicacao(novo.getAnoPublicacao());
        livro.setDisponivel(novo.isDisponivel());
        livro.setAutorId(novo.getAutorId());

        return repository.save(livro);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}