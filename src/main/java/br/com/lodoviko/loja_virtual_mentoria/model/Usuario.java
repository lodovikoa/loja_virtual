package br.com.lodoviko.loja_virtual_mentoria.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtualSenha;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_acesso",
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "acesso_id"}, name = "unique_acesso_user"),
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "tb_usuario", unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "acesso_id", unique = false, referencedColumnName = "id", table = "tb_acesso", foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Acesso> acessos;

    /* Autoridades = São os acessos, ou seja: ROLE_ADMIN, ROLE_SECRETARIO, ROLE_FINANCEIRO */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
