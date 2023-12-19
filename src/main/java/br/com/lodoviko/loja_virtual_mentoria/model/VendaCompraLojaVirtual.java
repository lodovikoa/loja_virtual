package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.enuns.StatusVendaLojaVirtual;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ItemVendaLojaExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoExibirReduzidoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_vd_cp_loja_virt")
public class VendaCompraLojaVirtual implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = PessoaFisica.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaFisica pessoa;

    @ManyToOne()
    @JoinColumn(name = "endereco_entrega_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_entrega_fk"))
    private Endereco enderecoEntrega;

    @ManyToOne()
    @JoinColumn(name = "endereco_cobranca_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_cobranca_fk"))
    private Endereco enderecoCobranca;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "forma_pagamento_fk"))
    private FormaPagamento formaPagamento;

    @OneToOne()
    @JoinColumn(name = "nota_fiscal_venda_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_venda_fk"))
    private  NotaFiscalVenda notaFiscalVenda;

    @ManyToOne
    @JoinColumn(name = "cupom_desconto_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cupom_desconto_fk"))
    private CupomDesconto cupomDesconto;

    @Column(nullable = false)
    private BigDecimal valorFrete;

    @Column(nullable = false)
    private Integer diaEntrega;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    @ManyToOne(targetEntity = PessoaJuridica.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVendaLojaVirtual statusVendaLojaVirtual;

    @OneToMany(mappedBy = "vendaCompraLojaVirtual", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemVendaLoja> itensVendaLoja;

    private Boolean excluido = Boolean.FALSE;

    @Column(name = "codigo_etiqueta")
    private String codigoEtiqueta;

    @Column(name = "url_imprime_etiqueta")
    private String urlImprimeEtiqueta;

    /* Frete que foi escolhido no momento da compra */
    @Column(name = "servico_transportadora")
    private String servicoTransportadora;

    public List<ItemVendaLojaExibirDTO> converterItens(List<ItemVendaLoja> itensVendaLoja) {
       List<ItemVendaLojaExibirDTO> itens = new ArrayList<>();

       for (int i = 0; i < itensVendaLoja.size(); i++) {
           ItemVendaLojaExibirDTO item = new ItemVendaLojaExibirDTO(itensVendaLoja.get(i).getId(), itensVendaLoja.get(i).getQuantidade(),
                   new ProdutoExibirReduzidoDTO(
                           itensVendaLoja.get(i).getProduto().getId(),
                           itensVendaLoja.get(i).getProduto().getTipoUnidade(),
                           itensVendaLoja.get(i).getProduto().getNome()));
           itens.add(item);

       }
       return itens;
    }
}
