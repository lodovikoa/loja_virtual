package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_marca_produto")
public class MarcaProduto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(nullable = false)
    @Getter @Setter private String nomeDesc;
}
