package com.willardy.algafood.domain.service;

import com.willardy.algafood.domain.exception.EntidadeEmUsoException;
import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser reomvida, pois está em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha saveOrUpdate(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id));

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Nao existe um cadastro de cozinha com código %d", id));
        }
    }

    public Cozinha buscaOuFalha(Long id){
        return cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_COZINHA_EM_USO));
    }
}
