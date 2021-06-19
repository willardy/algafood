package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Permissao;
import com.willardy.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PermissaoRepositoryJPA implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> all() {
        return manager.createQuery("from Permissao").getResultList();
    }

    @Override
    public Permissao findById(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao save(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public void remove(Permissao permissao) {
        permissao = findById(permissao.getId());
        manager.remove(permissao);
    }
}
