package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryJPA implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> all() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha save(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remove(Cozinha cozinha) {
        cozinha = findById(cozinha.getId());
        manager.remove(cozinha);
    }
}
