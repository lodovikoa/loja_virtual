package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoCadastrarDTO(
        Long id,
        @NotNull(message = "O tipo da unidade deve ser informado")
        String tipoUnidade,
        @Size(min = 10, message = "Nome do produto deve ter mais de 10 letras")
        @NotNull(message = "Nome do produto deve ser informado")
        String nome,
        Boolean ativo,
        @NotNull(message = "Descrição do produto deve ser informada")
        String descricao,
        @NotNull(message = "Peso deve ser informado")
        Double peso,
        @NotNull(message = "Largura deve ser informado")
        Double largura,
        @NotNull(message = "Altura deve ser informado")
        Double altura,
        @NotNull(message = "Profundidade deve ser informado")
        Double profundidade,
        @NotNull(message = "Valor venda deve ser informado")
        BigDecimal valorVenda,
        Integer qtdEstoque,
        Integer qtdAlertaEstoque,
        String linkYoutube,
        Boolean alertaQtdEstoque,
        Integer qtdClique,
        @NotNull(message = "A empresa responsável deve ser informada")
        PessoaJuridica empresa,
        @NotNull(message = "A Categoria do Produto deve ser informada")
        CategoriaProduto categoriaProduto,
        @NotNull(message = "A Marca do Produto deve ser informada")
        MarcaProduto marcaProduto
) {
}
