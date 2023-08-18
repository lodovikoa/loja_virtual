package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.controller.PessoaController;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Calendar;

@Profile("tst")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class PessoaUserTestes extends TestCase {

    @Autowired
    private PessoaController pessoaController;

    @Test
    public void CadPessoaTeste() throws Exception{
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Alex Fernandes");
        pessoaJuridica.setEmail("alex@gmail.com");
        pessoaJuridica.setTelefone("9999999999");
        pessoaJuridica.setInscEstadual("111111111111111");
        pessoaJuridica.setInscMunicipal("22222222222222");
        pessoaJuridica.setNomeFantasia("Nome fantasia");
        pessoaJuridica.setRazaoSocial("Empresa Teste Principal");

        pessoaController.salvarPJ(pessoaJuridica);

    }


}