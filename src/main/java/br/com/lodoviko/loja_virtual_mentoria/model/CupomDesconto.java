package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String codigoDesc;

    private BigDecimal valorRealDesc;
    private BigDecimal valorPorcentDesc;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataValidadeCupom;
}
