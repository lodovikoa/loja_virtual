package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioProdutoAlertaEstoqueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long codigoProduto;
    private String nomeProduto;
    private BigDecimal valorVendaProduto;
    private Double quantidadeComprada;
    private Long codigoFornecedor;
    private String nomeFornecedor;
    private LocalDate dataCompra;
    private Double quantidadeEstoque;
    private Double quantidadeAlertaEstoque;
}
