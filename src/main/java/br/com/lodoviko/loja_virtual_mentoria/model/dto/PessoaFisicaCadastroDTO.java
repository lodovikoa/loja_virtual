package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

public record PessoaFisicaCadastroDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String tipoPessoa,

        @CPF(message = "CPF Inválido!")
        String cpf,
        Date dataNascimento,
        Long idEmpresa,
        List<EnderecoCadastrarDTO> enderecos
) {
}
