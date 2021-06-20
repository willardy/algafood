package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryJPA implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> all() {
        return manager.createQuery("from Restaurante").getResultList();
    }

    @Override
    public Restaurante findById(Long id) {
        return manager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante save(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        Restaurante restaurante = findById(id);

        if(restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(restaurante);
    }
}
