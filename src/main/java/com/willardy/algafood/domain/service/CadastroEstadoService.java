package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado saveOrUpdate(Estado estado){
        return estadoRepository.save(estado);
    }

    public void remove(Long id){
        try {
            estadoRepository.remove(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("O estado de id %d não existe", id));
        }
    }
}
