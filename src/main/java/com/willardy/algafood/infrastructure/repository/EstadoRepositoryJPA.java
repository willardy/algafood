package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void remove(Estado estado) {
        estado = findById(estado.getId());
        manager.remove(estado);
    }
}
