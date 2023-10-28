package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;

import java.math.BigDecimal;
import java.util.Date;

public record CupomDescontoExibirDTO(
        Long id,
        String codigoDesc,
        BigDecimal valorRealDesc,
        BigDecimal valorPorcentDesc,
        Date dataValidadeCupom,
        Long idEmpresa
) {
    public CupomDescontoExibirDTO(CupomDesconto cupomDesconto) {
        this(
                cupomDesconto.getId(),
                cupomDesconto.getCodigoDesc(),
                cupomDesconto.getValorRealDesc(),
                cupomDesconto.getValorPorcentDesc(),
                cupomDesconto.getDataValidadeCupom(),
                cupomDesconto.getEmpresa().getId()
        );
    }
}
