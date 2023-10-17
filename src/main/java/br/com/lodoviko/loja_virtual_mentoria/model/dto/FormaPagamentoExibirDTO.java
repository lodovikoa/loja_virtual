package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.FormaPagamento;

public record FormaPagamentoExibirDTO(
        Long id,
        String descricao,
        Long idEmpresa
) {
    public FormaPagamentoExibirDTO(FormaPagamento formaPagamento) {
        this(
                formaPagamento.getId(),
                formaPagamento.getDescricao(),
                formaPagamento.getEmpresa().getId()
        );
    }
}
