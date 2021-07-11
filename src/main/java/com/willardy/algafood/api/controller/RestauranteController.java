package com.willardy.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import com.willardy.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> all() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public Restaurante findById(@PathVariable Long id) {
        return cadastroRestauranteService.buscaOuFalha(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody Restaurante restaurante) {
        return cadastroRestauranteService.saveOrUpdate(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        return cadastroRestauranteService.saveOrUpdate(restauranteAtual);
    }

    @PatchMapping("/{id}")
    public Restaurante updateParctional(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(id);

        merge(campos, restauranteAtual);

        return update(id, restauranteAtual);
    }

    private void merge(Map<String, Object> campos, Restaurante restaurante) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomeProprierade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomeProprierade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restaurante, novoValor);
        });
    }
}
