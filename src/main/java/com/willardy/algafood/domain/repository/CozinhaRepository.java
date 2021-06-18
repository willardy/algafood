package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> all();

    Cozinha findById(Long id);

    Cozinha save(Cozinha cozinha);

    void remove(Cozinha cozinha);
}
