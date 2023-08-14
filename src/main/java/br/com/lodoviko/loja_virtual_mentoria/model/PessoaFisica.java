package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_fisica")
public class PessoaFisica extends Pessoa{

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

}
