package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PessoaUserService pessoaUserService;

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
}
