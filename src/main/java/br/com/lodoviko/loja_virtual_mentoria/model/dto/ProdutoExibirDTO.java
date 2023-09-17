package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import java.math.BigDecimal;

public record ProdutoExibirDTO(
        Long id,
        String tipoUnidade,
        String nome,
        Boolean ativo,
        String descricao,
        Double peso,
        Double largura,
        Double altura,
        Double profundidade,
        BigDecimal valorVenda,
        Integer qtdEstoque,
        Integer qtdAlertaEstoque,
        String linkYoutube,
        Boolean alertaQtdEstoque,
        Integer qtdClique,
        Long empresa,
        Long categoriaProduto,
        Long marcaProduto
) {
}
