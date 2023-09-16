package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;

public record CategoriaProdutoDTO(Long id, String nomeDesc, Long idEmpresa) {

    public CategoriaProdutoDTO(CategoriaProduto categoriaProduto) {
        this(categoriaProduto.getId(), categoriaProduto.getNomeDesc(), categoriaProduto.getEmpresa().getId());
    }
}
