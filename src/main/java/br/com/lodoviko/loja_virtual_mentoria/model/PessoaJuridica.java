package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaCadastrarDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @CNPJ(message = "CNPJ inválido")
    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String inscEstadual;

    private String inscMunicipal;
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    private String categoria;

    public PessoaJuridicaCadastrarDTO converterPessoaJuridicaCadastrarDTO(PessoaJuridica pessoaJuridica, List<EnderecoCadastrarDTO> enderecoCompletoDTOs) {

        return new PessoaJuridicaCadastrarDTO(pessoaJuridica.getId(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getEmail(),
                pessoaJuridica.getTelefone(),
                pessoaJuridica.getTipoPessoa(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getInscEstadual(),
                pessoaJuridica.getInscMunicipal(),
                pessoaJuridica.getNomeFantasia(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getCategoria(),
                enderecoCompletoDTOs);
    }
}
