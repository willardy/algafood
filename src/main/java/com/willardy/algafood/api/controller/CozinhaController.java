package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> all() {
        return cozinhaRepository.all();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha findById(@PathVariable Long id){
        return cozinhaRepository.findById(id);
    }
}
