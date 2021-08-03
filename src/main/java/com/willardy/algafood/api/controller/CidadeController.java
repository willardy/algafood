package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.exception.EstadoNaoEncontradoException;
import com.willardy.algafood.domain.exception.NegocioException;
import com.willardy.algafood.domain.model.Cidade;
import com.willardy.algafood.domain.repository.CidadeRepository;
import com.willardy.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> all() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{id}")
    public Cidade findById(@PathVariable Long id) {
        return cadastroCidadeService.buscaOuFalha(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save(@Valid @RequestBody Cidade cidade) {
        try {
            return cadastroCidadeService.saveOrUpdate(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Cidade update(@PathVariable Long id, @Valid @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(id);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return cadastroCidadeService.saveOrUpdate(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        cadastroCidadeService.remove(id);
    }
}
