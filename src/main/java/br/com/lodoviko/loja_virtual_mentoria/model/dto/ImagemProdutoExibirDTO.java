package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.ImagemProduto;

public record ImagemProdutoExibirDTO(
        Long id,
        String imagemOriginal,
        String imagemMiniatura,
        Long idProduto,
        Long idEmpresa
) {
    public ImagemProdutoExibirDTO(ImagemProduto imagemProduto) {
        this(
                imagemProduto.getId(),
                imagemProduto.getImagemOriginal(),
                imagemProduto.getImagemMiniatura(),
                imagemProduto.getProduto().getId(),
                imagemProduto.getEmpresa().getId()
        );
    }
}
