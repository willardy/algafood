package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante saveOrUpdate(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }
}
