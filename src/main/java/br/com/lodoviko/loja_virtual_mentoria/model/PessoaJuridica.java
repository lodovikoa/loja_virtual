package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String inscEstadual;

    private String inscMunicipal;
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    private String categoria;

}
