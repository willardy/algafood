package com.willardy.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends ResponseStatusException {

    public EntidadeEmUsoException(HttpStatus status, String mensagem) {
        super(status, mensagem);
    }

    public EntidadeEmUsoException(String mensagem) {
        this(HttpStatus.CONFLICT, mensagem);
    }
}
