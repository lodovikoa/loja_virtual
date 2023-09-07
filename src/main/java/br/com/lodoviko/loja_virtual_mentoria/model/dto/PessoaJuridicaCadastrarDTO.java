package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

public record PessoaJuridicaCadastrarDTO(
        Long id,

        @Size(min = 6, message = "O nome deve possuir no mínimo 6 letras.")
        @NotBlank(message = "Blank - Nome deve ser informado.")
        @NotNull(message = "NULL - Nome deve ser informado")
        String nome,

        @Email(message = "E-mail inválido")
        String email,
        String telefone,
        String tipoPessoa,

        @CNPJ(message = "CNPJ inválido")
        String cnpj,
        String inscEstadual,
        String inscMunicipal,
        String nomeFantasia,
        String razaoSocial,
        String categoria,
        Long idEmpresa,
        List<EnderecoCadastrarDTO> enderecos
) {
}
