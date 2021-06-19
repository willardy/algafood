package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Cidade;
import com.willardy.algafood.domain.repository.CidadeRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CidadeRepositoryJPA implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> all() {
        return manager.createQuery("from Cidade").getResultList();
    }

    @Override
    public Cidade findById(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade save(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public void remove(Cidade cidade) {
        cidade = findById(cidade.getId());
        manager.remove(cidade);
    }
}
