package com.willardy.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

//@JsonRootName("cozinha") //Muda o rootname do retorno do cliente (nao visível em Json por nao mostrar o nome root)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
//    @JsonProperty("titulo") //Muda no retorno ao cliente
//    @JsonIgnore // propriedade que ignora no retorno ao cliente
    private String nome;
}
