package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.FormaPagamento;
import com.willardy.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FormaPagamentoRepositoryJPA implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> all() {
        return manager.createQuery("from FormaPagamento").getResultList();
    }

    @Override
    public FormaPagamento findById(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento save(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    public void remove(FormaPagamento formaPagamento) {
        formaPagamento = findById(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
