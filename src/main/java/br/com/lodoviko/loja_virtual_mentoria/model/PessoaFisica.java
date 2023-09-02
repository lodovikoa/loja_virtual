package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaCadastroDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaExibirDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_fisica")
public class PessoaFisica extends Pessoa{

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public PessoaFisica converterCadastrarDTOPessoaFisica(PessoaFisicaCadastroDTO pessoaFisicaCadastroDTO) {

     //   Endereco endereco = new Endereco();

        this.setId(pessoaFisicaCadastroDTO.id());
        this.setNome(pessoaFisicaCadastroDTO.nome());
        this.setEmail(pessoaFisicaCadastroDTO.email());
        this.setTelefone(pessoaFisicaCadastroDTO.telefone());
        this.setTipoPessoa(pessoaFisicaCadastroDTO.tipoPessoa());
        this.setCpf(pessoaFisicaCadastroDTO.cpf());
        this.setDataNascimento(pessoaFisicaCadastroDTO.dataNascimento());
       // this.setEmpresa(pessoaFisicaCadastroDTO.empresa());
       // this.setEnderecos(endereco.converterCadastrarEnderecoDTOEndereco(pessoaFisicaCadastroDTO.enderecos()));

        return this;
    }

    public PessoaFisicaExibirDTO converterPessoaFisicaExibirDTO() {
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

        return new PessoaFisicaExibirDTO(
                this.getId(),
                this.getNome(),
                this.getEmail(),
                this.getTelefone(),
                this.getTipoPessoa(),
                this.getCpf(),
                this.getDataNascimento(),
                enderecoExibirDTOS
                );
    }
}
