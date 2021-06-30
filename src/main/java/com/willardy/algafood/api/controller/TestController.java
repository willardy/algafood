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
import java.util.Optional;

@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas")
    public List<Cozinha> findByName(@RequestParam("nome") String name) {
        return cozinhaRepository.findByName(name);
    }

    @GetMapping("/restaurantes")
    public List<Restaurante> findByRestaurante(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> findByRestauranteWithFreteGratis(String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/primeiro-restaurante")
    public Optional<Restaurante> findByPrimeiroRestaurante() {
        return restauranteRepository.buscarPrimeiro();
    }

    @GetMapping("/cozinhas/primeira-cozinha")
    public Optional<Cozinha> findByPrimeiraCozinha() {
        return cozinhaRepository.buscarPrimeiro();
    }
}
