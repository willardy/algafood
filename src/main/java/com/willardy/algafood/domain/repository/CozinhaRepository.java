package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> all();

    List<Cozinha> findByName(String name);

    Cozinha findById(Long id);

    Cozinha save(Cozinha cozinha);

    void remove(Long id);
}
