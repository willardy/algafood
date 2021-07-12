package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.exception.CidadeNaoEncontradaException;
import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Cidade;
import com.willardy.algafood.domain.model.Estado;
import com.willardy.algafood.domain.repository.CidadeRepository;
import com.willardy.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe cadastro de estado com código %d";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    public Cidade saveOrUpdate(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstadoService.buscaOuFalha(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void remove(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        }
    }

    public Cidade buscaOuFalha(Long id){
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }
}
