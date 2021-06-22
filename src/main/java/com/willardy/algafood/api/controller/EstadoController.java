package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import com.willardy.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        List<Estado> estados = estadoRepository.all();

        return ResponseEntity.status(HttpStatus.OK).body(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        Estado estado = estadoRepository.findById(id);

        if (estado != null) {
            return ResponseEntity.ok().body(estado);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado) {
        Estado estadoAtual = estadoRepository.findById(id);

        try {
            if (estadoAtual != null) {
                BeanUtils.copyProperties(estado, estadoAtual, "id");
                estado = cadastroEstadoService.saveOrUpdate(estadoAtual);

                return ResponseEntity.ok(estado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Estado> remove(@PathVariable Long id) {
        try {
            cadastroEstadoService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody Estado estado){
        try {
            estado = cadastroEstadoService.saveOrUpdate(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
