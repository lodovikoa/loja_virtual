package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.enuns.TipoEndereco;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoCadastrarDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruaLogra;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String uf;

    @Column(nullable = false)
    private String cidade;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private Pessoa empresa;

    public EnderecoCadastrarDTO converterEnderecoCadastroDTO(Endereco endereco) {
        return new EnderecoCadastrarDTO(endereco.getId(),
                endereco.getRuaLogra(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getUf(),
                endereco.getCidade(),
                endereco.getTipoEndereco(),
                endereco.getPessoa(),
                endereco.getEmpresa()
                );
    }

    public List<Endereco> converterCadastrarEnderecoDTOEndereco(List<EnderecoCadastrarDTO> enderecoCadastrarDTOS) {

        List<Endereco> enderecos = new ArrayList<>();

        for (EnderecoCadastrarDTO dto : enderecoCadastrarDTOS) {

            Endereco endereco = new Endereco();

            endereco.setRuaLogra(dto.ruaLogra());
            endereco.setCep(dto.cep());
            endereco.setNumero(dto.numero());
            endereco.setComplemento(dto.complemento());
            endereco.setBairro(dto.bairro());
            endereco.setUf(dto.uf());
            endereco.setCidade(dto.cidade());
            endereco.setTipoEndereco(dto.tipoEndereco());
            endereco.setPessoa(dto.pessoa());
            endereco.setEmpresa(dto.empresa());

            enderecos.add(endereco);
        }

        return enderecos;
    }
}
