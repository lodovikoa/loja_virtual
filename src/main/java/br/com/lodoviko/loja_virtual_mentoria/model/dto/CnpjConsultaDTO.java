package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record CnpjConsultaDTO(
        String abertura,
        String situacao,
        String tipo,
        String nome,
        String fantasia,
        String porte,
        String natureza_juridica,
        List<CnpjAtividadeDTO> atividade_principal,
        String logradouro,
        String numero,
        String complemento,
        String municipio,
        String bairro,
        String uf,
        String cep,
        String email,
        String telefone,
        String data_situacao,
        String cnpj,
        String ultima_atualizacao,
        String status,
        String efr,
        String motivo_situacao,
        String situacao_especial,
        String data_situacao_especial,
        List<CnpjAtividadeDTO> atividades_secundarias,
        String capital_social,
        List<CnpjQsaDTO> qsa,
        @JsonIgnore
        CnpjExtraDTO extra,
        CnpjBilling billing
) {
}
