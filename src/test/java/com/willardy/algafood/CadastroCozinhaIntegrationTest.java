package com.willardy.algafood;

import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

//Configuração do JUNIT 5
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CadastroCozinhaIntegrationTest {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        //Cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Piauiense");

        //Ação
        cozinha = cadastroCozinhaService.saveOrUpdate(cozinha);

        //Validação
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();
    }

    @Test
    public void testarCadastroCozinhaSemNome() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class,
                () -> cadastroCozinhaService.saveOrUpdate(cozinha)
        );

        assertThat(erroEsperado).isNotNull();
    }
}
