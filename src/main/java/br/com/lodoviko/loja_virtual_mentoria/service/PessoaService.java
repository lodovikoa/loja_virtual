package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaCadastroDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaFisicaRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaUserService pessoaUserService;

//    public PessoaJuridica findReferenceById(Long id) {
//        return pessoaJuridicaRepository.getReferenceById(id);
//    }

    // Salvar Pessoa Juridica
    public PessoaJuridica salvarPJ(PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava {

        if(pessoaJuridica == null) {
            throw new ExceptionMentoriaJava("Pessoa Juridica não pode ser nullo");
        }

        if(pessoaJuridica.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Pessoa Juridica");
        }

        if(!pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj()).isEmpty()) {
            throw new ExceptionMentoriaJava("CNPJ: (" + pessoaJuridica.getCnpj() + ") Já está cadastrado.");
        }

        if(!pessoaJuridicaRepository.findByInscEstadual(pessoaJuridica.getInscEstadual()).isEmpty()){
            throw new ExceptionMentoriaJava("Insc Estadual (" + pessoaJuridica.getInscEstadual() + ") Já está cadastrada.");
        }

        for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
            pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
            pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
        }

        pessoaJuridica =  pessoaJuridicaRepository.save(pessoaJuridica);

        pessoaUserService.cadastrarUsuario(pessoaJuridica);

        return pessoaJuridica;
    }

    public PessoaFisicaExibirDTO salvarPF(PessoaFisicaCadastroDTO pessoaFisicaCadastroDTO) throws ExceptionMentoriaJava {

        // Validações
        if(pessoaFisicaCadastroDTO == null)
            throw new ExceptionMentoriaJava("Faltou informar dados da Pessoa Física.");

        if(pessoaFisicaCadastroDTO.id() != null)
            throw new ExceptionMentoriaJava("Não informar Id no cadastro de Pessoa Física.");

        if(!pessoaFisicaRepository.findByCpf(pessoaFisicaCadastroDTO.cpf()).isEmpty()) {
            throw new ExceptionMentoriaJava("CPF (" + pessoaFisicaCadastroDTO.cpf() + ") já está cadastrado.");
        }

        PessoaFisica pessoaFisica = new PessoaFisica();
        Endereco endereco = new Endereco();

        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByPrimaryKey(pessoaFisicaCadastroDTO.idEmpresa());

        pessoaFisica = pessoaFisica.converterCadastrarDTOPessoaFisica(pessoaFisicaCadastroDTO);
        pessoaFisica.setEmpresa(pessoaJuridica);



        List<Endereco> enderecos = endereco.converterCadastrarEnderecoDTOEndereco(pessoaFisicaCadastroDTO.enderecos());

        for (Endereco end : enderecos) {
            end.setPessoa(pessoaFisica);
            end.setEmpresa(pessoaJuridica);
            pessoaFisica.getEnderecos().add(end);
        }

        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

        pessoaUserService.cadastrarUsuario(pessoaFisica);

        return  pessoaFisica.converterPessoaFisicaExibirDTO();
    }

}
