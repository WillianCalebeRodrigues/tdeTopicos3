package br.upf.service;

import br.upf.entity.*;
import br.upf.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BibliotecaService {

    @Inject
    private AutorRepository autorRepository;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private EmprestimoRepository emprestimoRepository;

    // Métodos para listar dados

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.findAtivos();
    }

    // Métodos de contagem para estatísticas

    public Long contarTotalLivros() {
        return livroRepository.count();
    }

    public Long contarLivrosDisponiveis() {
        return livroRepository.countByDisponivel(true);
    }

    public Long contarEmprestimosAtivos() {
        return emprestimoRepository.countAtivos();
    }

    public Long contarTotalAutores() {
        return autorRepository.count();
    }
}