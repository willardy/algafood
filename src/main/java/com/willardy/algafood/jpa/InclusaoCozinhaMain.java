package com.willardy.algafood.jpa;

import com.willardy.algafood.ApiAlgafoodApplication;
import com.willardy.algafood.domain.model.Cozinha;
import com.willardy.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(ApiAlgafoodApplication.class).web(WebApplicationType.NONE).run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha("Brasileira");
        Cozinha cozinha2 = new Cozinha("Japonesa");

        cozinhaRepository.save(cozinha1);
        cozinhaRepository.save(cozinha2);
    }
}
