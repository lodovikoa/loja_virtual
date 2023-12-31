package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoExibirDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoUnidade;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(columnDefinition = "text", length = 2000, nullable = false)
    private String descricao;

    /** Nota Item Produto - ASSOCIAR **/

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double largura;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double profundidade;

    @Column(nullable = false)
    private BigDecimal valorVenda;

    @Column(nullable = false)
    private Integer qtdEstoque = 0;

    private Integer qtdAlertaEstoque = 0;
    private String linkYoutube;
    private Boolean alertaQtdEstoque;
    private Integer qtdClique = 0;

    @OneToMany(mappedBy = "produto", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagemProduto> imagens;

    @ManyToOne(targetEntity = PessoaJuridica.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;

    @ManyToOne(targetEntity = CategoriaProduto.class)
    @JoinColumn(name = "categoria_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "categoria_produto_id_fk"))
    private CategoriaProduto categoriaProduto;

    @ManyToOne(targetEntity = MarcaProduto.class)
    @JoinColumn(name = "marca_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "marca_produto_id_fk"))
    private MarcaProduto marcaProduto;

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(ProdutoCadastrarDTO produtoCadastrarDTO) {
        this.id =  produtoCadastrarDTO.id();
        this.tipoUnidade =  produtoCadastrarDTO.tipoUnidade();
        this.nome = produtoCadastrarDTO.nome();
        this.ativo =  produtoCadastrarDTO.ativo();
        this.descricao = produtoCadastrarDTO.descricao();
        this.peso = produtoCadastrarDTO.peso();
        this.largura = produtoCadastrarDTO.largura();
        this.altura = produtoCadastrarDTO.altura();
        this.profundidade = produtoCadastrarDTO.profundidade();
        this.valorVenda = produtoCadastrarDTO.valorVenda();
        this.qtdEstoque = produtoCadastrarDTO.qtdEstoque();
        this.qtdAlertaEstoque = produtoCadastrarDTO.qtdAlertaEstoque();
        this.linkYoutube = produtoCadastrarDTO.linkYoutube();
        this.alertaQtdEstoque = produtoCadastrarDTO.alertaQtdEstoque();
        this.qtdClique = produtoCadastrarDTO.qtdClique();
        this.peso = produtoCadastrarDTO.peso();
        this.imagens = produtoCadastrarDTO.imagens();
        this.empresa = produtoCadastrarDTO.empresa();
        this.categoriaProduto =produtoCadastrarDTO.categoriaProduto();
        this.marcaProduto = produtoCadastrarDTO.marcaProduto();
    }

    public ProdutoExibirDTO converterProdutoExibirDTO() {
        return new ProdutoExibirDTO(this.id,
                this.tipoUnidade,
                this.nome,
                this.ativo,
                this.descricao,
                this.peso,
                this.largura,
                this.altura,
                this.profundidade,
                this.valorVenda,
                this.qtdEstoque,
                this.qtdAlertaEstoque,
                this.linkYoutube,
                this.alertaQtdEstoque,
                this.qtdClique,
                this.empresa.getId(),
                this.categoriaProduto.getId(),
                this.marcaProduto.getId());
    }
}
