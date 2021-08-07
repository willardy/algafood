package com.willardy.algafood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willardy.algafood.core.validation.ValidacaoException;
import com.willardy.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.willardy.algafood.domain.exception.NegocioException;
import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import com.willardy.algafood.domain.service.CadastroRestauranteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private SmartValidator validator;

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
    public Restaurante save(@RequestBody @Valid Restaurante restaurante) {
        try {
            return cadastroRestauranteService.saveOrUpdate(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante update(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return cadastroRestauranteService.saveOrUpdate(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Restaurante updateParctional(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(id);

        merge(campos, restauranteAtual, request);
        valiate(restauranteAtual, "restaurante");

        return update(id, restauranteAtual);
    }

    private void valiate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> campos, Restaurante restaurante, HttpServletRequest request) {
        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);

            campos.forEach((nomeProprierade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomeProprierade);
                Objects.requireNonNull(field).setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restaurante, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
        }
    }
}
