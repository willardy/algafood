package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas")
    public List<Cozinha> findByName(@RequestParam("nome") String name){
        return cozinhaRepository.findByName(name);
    }

    @GetMapping("/restaurantes")
    public List<Restaurante> findByRestaurante(@RequestParam("nome") String name, @RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal){
        return restauranteRepository.find(name, taxaInicial, taxaFinal);
    }
}
