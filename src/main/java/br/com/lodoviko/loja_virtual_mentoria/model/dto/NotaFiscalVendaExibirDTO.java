package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalVenda;

public record NotaFiscalVendaExibirDTO(
        Long id,
        String numero,
        String serie,
        String tipo,
        String xml,
        String pdf,
        Long idVenda,
        Long idEmpresa
) {
    public NotaFiscalVendaExibirDTO(NotaFiscalVenda notaFiscalVenda) {
        this(notaFiscalVenda.getId(),
                notaFiscalVenda.getNumero(),
                notaFiscalVenda.getSerie(),
                notaFiscalVenda.getTipo(),
                notaFiscalVenda.getXml(),
                notaFiscalVenda.getPdf(),
                notaFiscalVenda.getVendaCompraLojaVirtual().getId(),
                notaFiscalVenda.getEmpresa().getId());
    }
}
