package com.algaworks.sistemaprodutos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Entity
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    private BigDecimal preco;
}
