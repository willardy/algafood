package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> all();

    FormaPagamento findById(Long id);

    FormaPagamento save(FormaPagamento formaPagamento);

    void remove(FormaPagamento formaPagamento);
}
