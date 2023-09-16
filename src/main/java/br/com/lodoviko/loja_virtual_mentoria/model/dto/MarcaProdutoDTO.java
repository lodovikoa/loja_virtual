package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;

public record MarcaProdutoDTO(Long id, String nomeDesc, Long idEmpresa) {

    public MarcaProdutoDTO(MarcaProduto marcaProduto) {
        this(marcaProduto.getId(), marcaProduto.getNomeDesc(), marcaProduto.getEmpresa().getId());
    }

}
