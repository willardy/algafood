package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import com.willardy.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> all() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/{id}")
    public Cozinha findById(@PathVariable Long id) {
        return cadastroCozinhaService.buscaOuFalha(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody @Valid Cozinha cozinha) {
        return cadastroCozinhaService.saveOrUpdate(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha updateById(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscaOuFalha(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cadastroCozinhaService.saveOrUpdate(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        cadastroCozinhaService.remove(id);
    }
}
