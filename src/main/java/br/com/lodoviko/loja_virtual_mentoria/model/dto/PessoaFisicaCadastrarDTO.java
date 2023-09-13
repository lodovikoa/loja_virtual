package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

public record PessoaFisicaCadastrarDTO(
        Long id,

        @Size(min = 6, message = "O nome deve possuir no mínimo 6 letras.")
        @NotBlank(message = "Blank - Nome deve ser informado.")
        @NotNull(message = "NULL - Nome deve ser informado")
        String nome,

        @Email(message = "E-mail inválido")
        String email,
        String telefone,
        String tipoPessoa,

        @CPF(message = "CPF Inválido!")
        String cpf,
        Date dataNascimento,
        Long idEmpresa,
        List<EnderecoCadastrarDTO> enderecos
) {
        public void atribuirTipoPessoa(String tipoPessoa) {
                tipoPessoa = this.tipoPessoa;
        }
}
