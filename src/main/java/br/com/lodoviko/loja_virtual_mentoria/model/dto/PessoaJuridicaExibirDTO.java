package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import java.util.List;

public record PessoaJuridicaExibirDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String tipoPessoa,
        String cnpj,
        String inscEstadual,
        String inscMunicipal,
        String nomeFansasia,
        String razaoSocial,
        String categoria,
        List<EnderecoExibirDTO> enderecos
) {
}
