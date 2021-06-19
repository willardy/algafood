package com.willardy.algafood.domain.repository;

import com.willardy.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> all();

    Permissao findById(Long id);

    Permissao save(Permissao permissao);

    void remove(Permissao permissao);
}
