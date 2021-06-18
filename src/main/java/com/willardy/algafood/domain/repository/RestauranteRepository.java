package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> all();

    Restaurante findById(Long id);

    Restaurante save(Restaurante restaurante);

    void remove(Restaurante restaurante);
}
