package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record NotaFiscalCompraCadastrarDTO(
        Long id,

        @NotNull(message = "Faltou informar o número da nota")
        String numeroNota,

        @NotNull(message = "Faltou informar a Série da Nota")
        String serieNota,

        String descricaoObs,

        @NotNull(message = "Faltou informar o Valor Total da Nota")
        BigDecimal valorTotal,

        BigDecimal valorDesconto,

        @NotNull(message = "Faltou informar o valor do ICMS")
        BigDecimal valorIcms,

        @NotNull(message = "Faltou informar a data da Compra")
        Date dataCompra,

        @NotNull(message = "faltou informar a Pessoa Juridica emissor da Nota de compra")
        PessoaJuridica pessoa,

        @NotNull(message = "Faltou informar a Conta Pagar relacionada")
        ContaPagar contaPagar,

        @NotNull(message = "Faltou informar a Empresa.")
        PessoaJuridica empresa
) {
    public NotaFiscalCompra converterDTO() {
        return new NotaFiscalCompra(
                this.id,
                this.numeroNota,
                this.serieNota,
                this.descricaoObs,
                this.valorTotal,
                this.valorDesconto,
                this.valorIcms,
                this.dataCompra,
                this.pessoa,
                this.contaPagar,
                this.empresa
        );
    }
}
