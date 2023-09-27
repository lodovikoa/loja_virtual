package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;

import java.math.BigDecimal;
import java.util.Date;

public record NotaFiscalCompraExibirDTO(
        Long id,
        String numeroNota,
        String serieNota,
        String descricaoObs,
        BigDecimal valorTotal,
        BigDecimal valorDesconto,
        BigDecimal valorIcms,
        Date dataCompra,
        Long  idPessoa,
        Long idContaPagar,
        Long idEmpresa
) {
    public NotaFiscalCompraExibirDTO(NotaFiscalCompra notaFiscalCompra) {
        this(
                notaFiscalCompra.getId(),
                notaFiscalCompra.getNumeroNota(),
                notaFiscalCompra.getSerieNota(),
                notaFiscalCompra.getDescricaoObs(),
                notaFiscalCompra.getValorTotal(),
                notaFiscalCompra.getValorDesconto(),
                notaFiscalCompra.getValorIcms(),
                notaFiscalCompra.getDataCompra(),
                notaFiscalCompra.getPessoa().getId(),
                notaFiscalCompra.getContaPagar().getId(),
                notaFiscalCompra.getEmpresa().getId()
        );
    }
}
