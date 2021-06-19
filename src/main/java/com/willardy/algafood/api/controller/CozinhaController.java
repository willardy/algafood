package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public ResponseEntity<List<Cozinha>> all() {
        List<Cozinha> cozinhas = cozinhaRepository.all();

        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id);

        if (cozinha != null) {
            return ResponseEntity.ok().body(cozinha);
        }

        return ResponseEntity.notFound().build();
    }
}
