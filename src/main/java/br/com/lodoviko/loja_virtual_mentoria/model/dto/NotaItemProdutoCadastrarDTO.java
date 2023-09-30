package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaItemProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import jakarta.validation.constraints.NotNull;

public record NotaItemProdutoCadastrarDTO(
        Long id,
        @NotNull(message = "Faltou informar a quantidade!!")
        Double quantidade,

        @NotNull(message = "Faltou informar a Nota Fiscal de Compra!!")
        NotaFiscalCompra notaFiscalCompra,

        @NotNull(message = "Faltou informar o Produto!!")
        Produto produto,

        @NotNull(message = "Faltou informar a Empresa!!")
        PessoaJuridica empresa
) {
    public NotaItemProduto converterDTO() {
        return  new NotaItemProduto(
                this.id,
                this.quantidade,
                this.notaFiscalCompra,
                this.produto,
                this.empresa
        );
    }
}
