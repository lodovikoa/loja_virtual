package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.AvaliacaoProduto;

public record AvaliacaoProdutoExibirDTO(
        Long id,
        String descricao,
        Integer nota,
        Long idPessoa,
        Long idProduto,
        Long idEmpresa
) {
    public AvaliacaoProdutoExibirDTO(AvaliacaoProduto avaliacaoProduto) {
        this(avaliacaoProduto.getId(),
                avaliacaoProduto.getDescricao(),
                avaliacaoProduto.getNota(),
                avaliacaoProduto.getPessoa().getId(),
                avaliacaoProduto.getProduto().getId(),
                avaliacaoProduto.getEmpresa().getId()
            );
    }
}
