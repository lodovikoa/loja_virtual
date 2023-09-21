package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusContaPagar;

import java.math.BigDecimal;
import java.util.Date;

public record ContaPagarExibirDTO(
        Long id,
        String descricao,
        BigDecimal valorTotal,
        BigDecimal valorDesconto,
        StatusContaPagar status,
        Date dtVencimento,
        Date dtPagamento,
        Long idPessoa,
        Long idPessoaFornecedor,
        Long idEmpresa
) {
}
