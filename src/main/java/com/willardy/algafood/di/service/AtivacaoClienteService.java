package com.willardy.algafood.di.service;

import org.springframework.stereotype.Component;

import com.willardy.algafood.di.model.Cliente;
import com.willardy.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastrao no sistema está ativo!");
	}

}
