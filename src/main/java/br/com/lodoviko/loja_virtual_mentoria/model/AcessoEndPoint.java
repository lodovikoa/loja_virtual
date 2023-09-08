package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_acesso_end_point")
public class AcessoEndPoint implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeEndPoint;

    private Integer qtdeAcessos;

    private LocalDateTime ultimoAcesso;
}
