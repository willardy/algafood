package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> all();

    Cidade findById(Long id);

    Cidade save(Cidade cidade);

    void remove(Long id);
}
