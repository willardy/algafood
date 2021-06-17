package com.willardy.algafood.di.notificacao;

import com.willardy.algafood.di.model.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}