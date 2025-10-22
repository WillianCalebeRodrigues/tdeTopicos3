package br.upf.repository;

import br.upf.entity.Livro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class LivroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Retorna todos os livros, buscando também o autor para evitar lazy loading
    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT l FROM Livro l LEFT JOIN FETCH l.autor", Livro.class)
                            .getResultList();
    }

    // Retorna a contagem total de livros
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l", Long.class)
                            .getSingleResult();
    }

    // Retorna a contagem de livros por status de disponibilidade
    public Long countByDisponivel(boolean disponivel) {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l WHERE l.disponivel = :disponivel", Long.class)
                            .setParameter("disponivel", disponivel)
                            .getSingleResult();
    }
}