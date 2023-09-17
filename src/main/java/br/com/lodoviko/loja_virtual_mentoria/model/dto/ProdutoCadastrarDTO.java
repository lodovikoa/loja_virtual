package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;

import java.math.BigDecimal;

public record ProdutoCadastrarDTO(
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
        PessoaJuridica empresa,
        CategoriaProduto categoriaProduto,
        MarcaProduto marcaProduto
) {
}
