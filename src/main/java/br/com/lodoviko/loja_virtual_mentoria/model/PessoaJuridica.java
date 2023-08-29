package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoCompletoDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaCompletaDTO;
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

    public PessoaJuridicaCompletaDTO converterPessoaJuridicaCompletoDTO(PessoaJuridica pessoaJuridica, List<EnderecoCompletoDTO> enderecoCompletoDTOs) {

        return new PessoaJuridicaCompletaDTO(pessoaJuridica.getId(),
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
