package br.upf.repository;

import br.upf.entity.Autor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class AutorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Retorna todos os autores
    public List<Autor> findAll() {
        return entityManager.createQuery("SELECT a FROM Autor a", Autor.class)
                            .getResultList();
    }

    // Retorna a contagem total de autores
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Autor a", Long.class)
                            .getSingleResult();
    }
}