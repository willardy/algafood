package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import com.willardy.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> all() {
        List<Estado> estados = estadoRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(estados);
    }

    @GetMapping("/{id}")
    public Estado findById(@PathVariable Long id) {
        return cadastroEstadoService.buscaOuFalha(id);
    }

    @PutMapping("/{id}")
    public Estado update(@PathVariable Long id, @Valid @RequestBody Estado estado) {
        Estado estadoAtual = cadastroEstadoService.buscaOuFalha(id);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return cadastroEstadoService.saveOrUpdate(estadoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        cadastroEstadoService.remove(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado save(@Valid @RequestBody Estado estado) {
        return cadastroEstadoService.saveOrUpdate(estado);
    }
}
