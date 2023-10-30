package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.CupomDescontoExibirDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_cupom_desconto")
public class CupomDesconto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Informe o código do desconto")
    @Column(nullable = false)
    private String codigoDesc;

    private BigDecimal valorRealDesc;
    private BigDecimal valorPorcentDesc;

    @NotNull(message = "Informe a data de validade do cumpom.")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataValidadeCupom;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;

    public CupomDescontoExibirDTO converteDTO() {
        return new CupomDescontoExibirDTO(
                this.id,
                this.codigoDesc,
                this.valorRealDesc,
                this.valorPorcentDesc,
                this.dataValidadeCupom,
                this.empresa.getId()
        );
    }
}
