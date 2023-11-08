package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusVendaLojaVirtual;
import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;
import br.com.lodoviko.loja_virtual_mentoria.model.FormaPagamento;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalVenda;
import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record VendaCompraLojaVirtualExibirDTO(
        Long id,
        Long idPessoa,
        Long idEnderecoEntrega,
        Long idEnderecoCobranca,
        BigDecimal valorTotal,
        BigDecimal valorDesconto,
        FormaPagamento formaPagamento,
        NotaFiscalVenda notaFiscalVenda,
        CupomDesconto cupomDesconto,
        BigDecimal valorFrete,
        Integer diaEntrega,
        Date dataVenda,
        Date dataEntrega,
        StatusVendaLojaVirtual statusVendaLojaVirtual,
        Long idEmpresa,
        List<ItemVendaLojaExibirDTO> produtos
) {
   public VendaCompraLojaVirtualExibirDTO(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
       this(
               vendaCompraLojaVirtual.getId(), 
               vendaCompraLojaVirtual.getPessoa().getId(), 
               vendaCompraLojaVirtual.getEnderecoEntrega().getId(), 
               vendaCompraLojaVirtual.getEnderecoCobranca().getId(), 
               vendaCompraLojaVirtual.getValorTotal(), 
               vendaCompraLojaVirtual.getValorDesconto(), 
               vendaCompraLojaVirtual.getFormaPagamento(), 
               vendaCompraLojaVirtual.getNotaFiscalVenda(), 
               vendaCompraLojaVirtual.getCupomDesconto(), 
               vendaCompraLojaVirtual.getValorFrete(), 
               vendaCompraLojaVirtual.getDiaEntrega(), 
               vendaCompraLojaVirtual.getDataVenda(), 
               vendaCompraLojaVirtual.getDataEntrega(),
               vendaCompraLojaVirtual.getStatusVendaLojaVirtual(),
               vendaCompraLojaVirtual.getEmpresa().getId(), 
               vendaCompraLojaVirtual.converterItens(vendaCompraLojaVirtual.getItensVendaLoja())
       );
   }
}
