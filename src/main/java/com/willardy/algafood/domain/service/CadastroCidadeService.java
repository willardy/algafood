package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Cidade;
import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.CidadeRepository;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade saveOrUpdate(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de estado com código %d", estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void remove(Long id) {
        try {
            cidadeRepository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("A cidade com id %d não existe", id));
        }
    }

    public Cidade findById(Long id) {
        return cidadeRepository.findById(id);
    }
}
