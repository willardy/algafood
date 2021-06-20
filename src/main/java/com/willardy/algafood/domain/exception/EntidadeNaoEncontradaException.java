package com.willardy.algafood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensage) {
        super(mensage);
    }
}
