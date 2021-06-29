package com.willardy.algafood.infrastructure.repository;

import com.willardy.algafood.domain.model.Restaurante;
import com.willardy.algafood.domain.repository.RestauranteRepository;
import com.willardy.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.willardy.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.willardy.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> restauranteRoot = criteria.from(Restaurante.class);

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(restauranteRoot.get("nome"), "%" + nome + "%"));
        }

        if (taxaInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaInicial));
        }

        if (taxaFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(restauranteRoot.get("taxaFrete"), taxaFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }
}
