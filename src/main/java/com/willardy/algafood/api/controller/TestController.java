package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @GetMapping
//    public List<Cozinha> findByName(@RequestParam("nome") String name){
//        return cozinhaRepository.findByName(name);
//    }
}
