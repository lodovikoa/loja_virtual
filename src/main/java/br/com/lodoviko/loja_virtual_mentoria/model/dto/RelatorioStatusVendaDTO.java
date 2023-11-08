package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusVendaLojaVirtual;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioStatusVendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigoProduto;
    private String nomeProduto;
    private String emailCliente;
    private String foneCliente;
    private BigDecimal valorVendaProduto;
    private Long codigoCliente;
    private String nomeCliente;
    private Integer qtdEstoque;
    private Long codigoVenda;
    private StatusVendaLojaVirtual statusVenda;
}
