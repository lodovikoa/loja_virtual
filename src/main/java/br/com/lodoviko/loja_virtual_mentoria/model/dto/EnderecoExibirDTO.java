package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.TipoEndereco;

public record EnderecoExibirDTO(
        Long id,
        String ruaLogra,
        String cep,
        String numero,
        String complemento,
        String bairro,
        String uf,
        String cidade,
        TipoEndereco tipoEndereco
) {
}
