package br.com.lodoviko.loja_virtual_mentoria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "tb_acesso")
@EqualsAndHashCode(of = {"id"})
public class Acesso implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(nullable = false)
    @Getter @Setter private String descricao;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
