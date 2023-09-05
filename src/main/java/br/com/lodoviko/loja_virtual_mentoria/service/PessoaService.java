package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.*;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaFisicaRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaUserService pessoaUserService;

    // Salvar Pessoa Juridica
    public PessoaJuridicaExibirDTO salvarPJ(PessoaJuridicaCadastrarDTO pessoaJuridicaCadastrarDTO) throws ExceptionMentoriaJava {

        if(pessoaJuridicaCadastrarDTO == null) {
            throw new ExceptionMentoriaJava("Pessoa Juridica não pode ser nullo");
        }

        if(pessoaJuridicaCadastrarDTO.id() != null) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Pessoa Juridica");
        }

        if(!pessoaJuridicaRepository.findByCnpj(pessoaJuridicaCadastrarDTO.cnpj()).isEmpty()) {
            throw new ExceptionMentoriaJava("CNPJ: (" + pessoaJuridicaCadastrarDTO.cnpj() + ") Já está cadastrado.");
        }

        if(!pessoaJuridicaRepository.findByInscEstadual(pessoaJuridicaCadastrarDTO.inscEstadual()).isEmpty()){
            throw new ExceptionMentoriaJava("Insc Estadual (" + pessoaJuridicaCadastrarDTO.inscEstadual() + ") Já está cadastrada.");
        }

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        PessoaJuridica empresa = new PessoaJuridica();
        Endereco endereco = new Endereco();

        // Se informado o código da Empresa, busca-lo no banco
        if(pessoaJuridicaCadastrarDTO.idEmpresa() != null && pessoaJuridicaCadastrarDTO.idEmpresa() > 0) {
            empresa = pessoaJuridicaRepository.findByPrimaryKey(pessoaJuridicaCadastrarDTO.idEmpresa());
        }

        pessoaJuridica = pessoaJuridica.converterCadastrarDTOPessoaJuridica(pessoaJuridicaCadastrarDTO);
        pessoaJuridica.setEmpresa(empresa);

        List<Endereco> enderecos = endereco.converterCadastrarEnderecoDTOEndereco(pessoaJuridicaCadastrarDTO.enderecos());

        for (Endereco end : enderecos) {
            end.setPessoa(pessoaJuridica);
            end.setEmpresa(empresa);
            pessoaJuridica.getEnderecos().add(end);
        }

        pessoaJuridica =  pessoaJuridicaRepository.save(pessoaJuridica);
        pessoaUserService.cadastrarUsuario(pessoaJuridica);

        return pessoaJuridica.converterPessoaJuridicaCadastrarDTO();
    }

    public PessoaFisicaExibirDTO salvarPF(PessoaFisicaCadastrarDTO pessoaFisicaCadastrarDTO) throws ExceptionMentoriaJava {

        // Validações
        if(pessoaFisicaCadastrarDTO == null)
            throw new ExceptionMentoriaJava("Faltou informar dados da Pessoa Física.");

        if(pessoaFisicaCadastrarDTO.id() != null)
            throw new ExceptionMentoriaJava("Não informar Id no cadastro de Pessoa Física.");

        if(!pessoaFisicaRepository.findByCpf(pessoaFisicaCadastrarDTO.cpf()).isEmpty()) {
            throw new ExceptionMentoriaJava("CPF (" + pessoaFisicaCadastrarDTO.cpf() + ") já está cadastrado.");
        }

        PessoaFisica pessoaFisica = new PessoaFisica();
        Endereco endereco = new Endereco();

        PessoaJuridica empresa = pessoaJuridicaRepository.findByPrimaryKey(pessoaFisicaCadastrarDTO.idEmpresa());

        pessoaFisica = pessoaFisica.converterCadastrarDTOPessoaFisica(pessoaFisicaCadastrarDTO);
        pessoaFisica.setEmpresa(empresa);



        List<Endereco> enderecos = endereco.converterCadastrarEnderecoDTOEndereco(pessoaFisicaCadastrarDTO.enderecos());

        for (Endereco end : enderecos) {
            end.setPessoa(pessoaFisica);
            end.setEmpresa(empresa);
            pessoaFisica.getEnderecos().add(end);
        }

        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

        pessoaUserService.cadastrarUsuario(pessoaFisica);

        return  pessoaFisica.converterPessoaFisicaExibirDTO();
    }

    public CepDTO consultaCep(String cep) {
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
    }

}
