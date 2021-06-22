package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository {

    List<Estado> all();

    Estado findById(Long id);

    Estado save(Estado estado);

    void remove(Long id);
}
