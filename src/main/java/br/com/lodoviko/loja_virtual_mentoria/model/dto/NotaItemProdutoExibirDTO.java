package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaItemProduto;

public record NotaItemProdutoExibirDTO(
        Long id,
        Double quantidade,
        Long idNotaFiscalCompra,
        Long idProduto,
        Long idEmpresa
) {
    public NotaItemProdutoExibirDTO(NotaItemProduto notaItemProduto) {
        this(
                notaItemProduto.getId(),
                notaItemProduto.getQuantidade(),
                notaItemProduto.getNotaFiscalCompra().getId(),
                notaItemProduto.getProduto().getId(),
                notaItemProduto.getEmpresa().getId()
        );
    }
}
