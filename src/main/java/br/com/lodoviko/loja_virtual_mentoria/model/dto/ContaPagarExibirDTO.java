package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;

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

    public ContaPagarExibirDTO(ContaPagar contaPagar) {
        this(
                contaPagar.getId(),
                contaPagar.getDescricao(),
                contaPagar.getValorTotal(),
                contaPagar.getValorDesconto(),
                contaPagar.getStatus(),
                contaPagar.getDtVencimento(),
                contaPagar.getDtPagamento(),
                contaPagar.getPessoa().getId(),
                contaPagar.getPessoaFornecedor().getId(),
                contaPagar.getEmpresa().getId()
        );
    }
}
