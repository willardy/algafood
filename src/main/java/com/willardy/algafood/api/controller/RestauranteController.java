package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public ResponseEntity<List<Restaurante>> all() {
        List<Restaurante> restaurantes = restauranteRepository.all();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.findById(id);

        return ResponseEntity.ok(restaurante);
    }
}
