package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.model.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record ContaPagarCadastrarDTO(
        Long id,
        @NotBlank(message = "Faltou informar a descrição")
        @NotNull(message = "Faltou informar a descrição")
        String descricao,
        @NotNull(message = "Faltou informar o valor total")
        BigDecimal valorTotal,
        BigDecimal valorDesconto,
        @NotNull(message = "Faltou informar o status")
        StatusContaPagar status,
        @NotNull(message = "Faltou informar a data de vencimento")
        Date dtVencimento,
        Date dtPagamento,
        @NotNull(message = "Faltou informar a Pessoa responável pela Conta a Pagar")
        Pessoa pessoa,
        @NotNull(message = "Faltou informar o Fornecedor")
        Pessoa pessoaFornecedor,
        @NotNull(message = "Faltou informar a Empresa")
        Pessoa empresa
) {
        public ContaPagar converterDTO() {
                return new ContaPagar(
                        this.id,
                        this.descricao,
                        this.valorTotal,
                        this.valorDesconto,
                        this.status,
                        this.dtVencimento,
                        this.dtPagamento,
                        this.pessoa,
                        this.pessoaFornecedor,
                        this.empresa
                );
        }
}
