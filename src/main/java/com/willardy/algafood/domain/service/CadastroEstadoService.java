package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.exception.EntidadeEmUsoException;
import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("O estado de id %d não existe", id));
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("O estado de id %d está sendo utilizado por outra tabela", id));
        }
    }
}
