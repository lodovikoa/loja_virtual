package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaRepository;
import br.com.lodoviko.loja_virtual_mentoria.service.PessoaUserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("tst")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class PessoaUserTestes extends TestCase {
    @Autowired
    private PessoaUserService pessoaUserService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void CadPessoaTeste() throws Exception{
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setCnpj("111111111111111111");
        pessoaJuridica.setNome("Alex Fernandes");
        pessoaJuridica.setEmail("alex@gmail.com");
        pessoaJuridica.setTelefone("9999999999");
        pessoaJuridica.setInscEstadual("111111111111111");
        pessoaJuridica.setInscMunicipal("22222222222222");
        pessoaJuridica.setNomeFantasia("Nome fantasia");
        pessoaJuridica.setRazaoSocial("Empresa Teste Principal");

        pessoaRepository.save(pessoaJuridica);

//        PessoaFisica pessoaFisica =  new PessoaFisica();


    }


}