package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaExibirDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @CNPJ(message = "CNPJ inválido")
    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String inscEstadual;

    private String inscMunicipal;
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    private String categoria;

    public PessoaJuridica converterCadastrarDTOPessoaJuridica(PessoaJuridicaCadastrarDTO pessoaJuridicaCadastrarDTO) {

        this.setId(pessoaJuridicaCadastrarDTO.id());
        this.setNome(pessoaJuridicaCadastrarDTO.nome());
        this.setEmail(pessoaJuridicaCadastrarDTO.email());
        this.setTelefone(pessoaJuridicaCadastrarDTO.telefone());
        this.setTipoPessoa(pessoaJuridicaCadastrarDTO.tipoPessoa());
        this.setCnpj(pessoaJuridicaCadastrarDTO.cnpj());
        this.setInscEstadual(pessoaJuridicaCadastrarDTO.inscEstadual());
        this.setInscMunicipal(pessoaJuridicaCadastrarDTO.inscMunicipal());
        this.setNomeFantasia(pessoaJuridicaCadastrarDTO.nomeFansasia());
        this.setRazaoSocial(pessoaJuridicaCadastrarDTO.razaoSocial());
        this.setCategoria(pessoaJuridicaCadastrarDTO.categoria());

        return this;
    }

    public PessoaJuridicaExibirDTO converterPessoaJuridicaCadastrarDTO() {

        List<EnderecoExibirDTO> enderecoExibirDTOS = new ArrayList<>();

        for(int i = 0; this.getEnderecos().size() > i; i++) {
            EnderecoExibirDTO enderecoExibirDTO = new EnderecoExibirDTO(
                    this.getEnderecos().get(i).getId(),
                    this.getEnderecos().get(i).getRuaLogra(),
                    this.getEnderecos().get(i).getCep(),
                    this.getEnderecos().get(i).getNumero(),
                    this.getEnderecos().get(i).getComplemento(),
                    this.getEnderecos().get(i).getBairro(),
                    this.getEnderecos().get(i).getUf(),
                    this.getEnderecos().get(i).getCidade(),
                    this.getEnderecos().get(i).getTipoEndereco()) ;
            enderecoExibirDTOS.add(enderecoExibirDTO);
        }

        return new PessoaJuridicaExibirDTO(
                this.getId(),
                this.getNome(),
                this.getEmail(),
                this.getTelefone(),
                this.getTipoPessoa(),
                this.getCnpj(),
                this.getInscEstadual(),
                this.getInscMunicipal(),
                this.getNomeFantasia(),
                this.getRazaoSocial(),
                this.getCategoria(),
                enderecoExibirDTOS
        );
    }
}
