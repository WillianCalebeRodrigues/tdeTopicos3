package br.upf.controller;


import br.upf.entity.Autor;
import br.upf.entity.Emprestimo;
import br.upf.entity.Livro;
import br.upf.service.BibliotecaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("bibliotecaBean")
@ViewScoped
public class BibliotecaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BibliotecaService bibliotecaService;

    private List<Autor> autores = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimosAtivos = new ArrayList<>();

    private long totalLivros;
    private long livrosDisponiveis;
    private long emprestimosAtivosCount;
    private long totalAutores;

    @PostConstruct
    public void init() {
        carregarDados();
        carregarEstatisticas();
    }

    public void carregarDados() {
        try {
            autores = bibliotecaService.listarTodosAutores();
            livros = bibliotecaService.listarTodosLivros();
            emprestimosAtivos = bibliotecaService.listarEmprestimosAtivos();
        } catch (Exception e) {
            // Aqui você pode usar um logger ou uma mensagem de feedback na UI
            e.printStackTrace();
            // Poderia adicionar mensagens FacesMessages para informar o usuário, se quiser
        }
    }

    public void carregarEstatisticas() {
        try {
            totalLivros = bibliotecaService.contarTotalLivros();
            livrosDisponiveis = bibliotecaService.contarLivrosDisponiveis();
            emprestimosAtivosCount = bibliotecaService.contarEmprestimosAtivos();
            totalAutores = bibliotecaService.contarTotalAutores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recarregarDados() {
        init();
    }

    // Getters

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public long getTotalLivros() {
        return totalLivros;
    }

    public long getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public long getEmprestimosAtivosCount() {
        return emprestimosAtivosCount;
    }

    public long getTotalAutores() {
        return totalAutores;
    }
}