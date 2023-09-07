package br.com.lodoviko.loja_virtual_mentoria.model;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaCadastrarDTO;
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

    public PessoaFisica converterCadastrarDTOPessoaFisica(PessoaFisicaCadastrarDTO pessoaFisicaCadastrarDTO) {
        this.setId(pessoaFisicaCadastrarDTO.id());
        this.setNome(pessoaFisicaCadastrarDTO.nome());
        this.setEmail(pessoaFisicaCadastrarDTO.email());
        this.setTelefone(pessoaFisicaCadastrarDTO.telefone());
        this.setTipoPessoa(pessoaFisicaCadastrarDTO.tipoPessoa());
        this.setCpf(pessoaFisicaCadastrarDTO.cpf());
        this.setDataNascimento(pessoaFisicaCadastrarDTO.dataNascimento());

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

    public List<PessoaFisicaExibirDTO> converterPessoaFisicaConsultarDTO(List<PessoaFisica> pessoaFisicas) {
        List<PessoaFisicaExibirDTO> pessoaFisicaExibirDTOS = new ArrayList<>();

        for (PessoaFisica pf : pessoaFisicas) {
            PessoaFisicaExibirDTO pfDTO = pf.converterPessoaFisicaExibirDTO();
            pessoaFisicaExibirDTOS.add(pfDTO);
        }

        return pessoaFisicaExibirDTOS;
    }
}
