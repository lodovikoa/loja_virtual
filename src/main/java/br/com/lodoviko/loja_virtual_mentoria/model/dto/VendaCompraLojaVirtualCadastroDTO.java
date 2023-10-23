package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record VendaCompraLojaVirtualCadastroDTO(
        Long id,
        @NotNull(message = "Faltou informar a pessoa que fez a compra.")
        PessoaFisica pessoa,
        @NotNull(message = "Faltou informar o Endereço de entrega")
        Endereco enderecoEntrega,
        @NotNull(message = "Faltou informar o Endereço de cobrança")
        Endereco enderecoCobranca,
        @NotNull(message = "Faltou informar o Valor Total da compra")
        @Min(value = 1, message = "Valor total inválido")
        BigDecimal valorTotal,

        BigDecimal valorDesconto,
        @NotNull(message = "Faltou informar a Forma de Pagamento")
        FormaPagamento formaPagamento,

        NotaFiscalVenda notaFiscalVenda,

        CupomDesconto cupomDesconto,
        @NotNull(message = "Faltou informar o Valor do Frete")
        BigDecimal valorFrete,

        @NotNull(message = "Faltou informar a quantidade de dias necessários para a entrega")
        @Min(value = 1)
        Integer diaEntrega,
        @NotNull(message = "Faltou informar a Data da Venda")
        Date dataVenda,
        @NotNull(message = "Faltou informar a Data de Entrega")
        Date dataEntrega,

        @NotNull(message = "Faltou informar a Empresa")
        PessoaJuridica empresa,
        List<ItemVendaLoja> itensVendaLoja,

        @JsonIgnore
        Boolean excluido
) {
    public VendaCompraLojaVirtual converterDTO() {
        return new VendaCompraLojaVirtual(this.id,
                this.pessoa,
                this.enderecoEntrega,
                this.enderecoCobranca,
                this.valorTotal,
                this.valorDesconto,
                this.formaPagamento,
                this.notaFiscalVenda,
                this.cupomDesconto,
                this.valorFrete,
                this.diaEntrega,
                this.dataVenda,
                this.dataEntrega,
                this.empresa,
                this.itensVendaLoja,
                this.excluido
            );
    }
}
