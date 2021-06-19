package com.willardy.algafood.api.controller;

import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> all(){
        List<Estado> estados = estadoRepository.all();

        return ResponseEntity.status(HttpStatus.OK).body(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id){
        Estado estado = estadoRepository.findById(id);

        if(estado != null){
            return ResponseEntity.ok().body(estado);
        }

        return ResponseEntity.notFound().build();
    }
}
