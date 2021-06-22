package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EstadoRepositoryJPA implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> all() {
        return manager.createQuery("from Estado").getResultList();
    }

    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    public void remove(Long id) {
        Estado estado = findById(id);

        if(estado == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(estado);
    }
}
