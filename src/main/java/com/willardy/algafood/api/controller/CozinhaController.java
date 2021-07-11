package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.exception.EntidadeEmUsoException;
import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import com.willardy.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok().body(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.saveOrUpdate(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> updateById(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha cozinhaNova = cadastroCozinhaService.saveOrUpdate(cozinhaAtual.get());

            return ResponseEntity.ok(cozinhaNova);
        }

        return ResponseEntity.notFound().build();
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Cozinha> remove(@PathVariable Long id) {
//        try {
//            cadastroCozinhaService.remove(id);
//            return ResponseEntity.noContent().build();
//        } catch (EntidadeEmUsoException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        } catch (EntidadeNaoEncontradaException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
            cadastroCozinhaService.remove(id);
    }
}
