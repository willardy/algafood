package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> all();

    Estado findById(Long id);

    Estado save(Estado estado);

    void remove(Estado estado);
}
