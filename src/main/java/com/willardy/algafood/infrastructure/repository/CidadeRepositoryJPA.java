package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Cidade;
import com.willardy.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CidadeRepositoryJPA implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> all() {
        return manager.createQuery("from Cidade").getResultList();
    }

    @Override
    public Cidade findById(Long id) {
        Cidade cidade = manager.find(Cidade.class, id);

        return cidade;
    }

    @Override
    @Transactional
    public Cidade save(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Cidade cidade = findById(id);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }


        manager.remove(cidade);
    }
}
