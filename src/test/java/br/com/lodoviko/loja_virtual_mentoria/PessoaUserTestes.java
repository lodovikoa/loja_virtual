package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.controller.PessoaController;
import br.com.lodoviko.loja_virtual_mentoria.enuns.TipoEndereco;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.*;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Profile("tst")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class PessoaUserTestes extends TestCase {

    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Test
    public void CadPessoaJuridicaTeste() throws Exception{
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setCnpj("33116181000175"); // + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("LUDOS Alex Fernandes");
        pessoaJuridica.setEmail("lodoviko@hotmail.com");
        pessoaJuridica.setTelefone("9999999999");
        pessoaJuridica.setInscEstadual("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setInscMunicipal("22222222222222");
        pessoaJuridica.setNomeFantasia("Nome fantasia");
        pessoaJuridica.setRazaoSocial("Empresa Teste Principal");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Jd Dias1");
        endereco1.setCep("28500000");
        endereco1.setComplemento("Casa rosa");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("1000");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogra("Rua vai quem quer");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setCidade("Curitiba");
        endereco1.setUf("PR");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Loteamento do meio2");
        endereco2.setCep("28545000");
        endereco2.setComplemento("Apto 15101");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("11");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogra("Rua dos Moleques");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setCidade("Maringa");
        endereco2.setUf("PR");

        pessoaJuridica.getEnderecos().add(endereco1);
        pessoaJuridica.getEnderecos().add(endereco2);

        PessoaJuridicaCadastrarDTO dto = pessoaController.salvarPJ(pessoaJuridica).getBody();

        assertEquals(true, dto.id() > 0);

        for(EnderecoCadastrarDTO endereco : dto.enderecos()) {
            assertEquals(true, endereco.id() > 0);
        }

        assertEquals(2, dto.enderecos().size());
    }

    @Test
    public void cadPessoaFisicaTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dtNascimento = formatter.parse("22/07/1964");

        EnderecoCadastrarDTO enderecoCadastrarDTO1 = new  EnderecoCadastrarDTO(
                null,
                "Rua de Cima",
                "29144-040",
                "123456",
                "Loja",
                "Bairro de cima",
                "ES",
                "Vitoria",
                TipoEndereco.ENTREGA,
                null,
                null
        );

        EnderecoCadastrarDTO enderecoCadastrarDTO2 = new  EnderecoCadastrarDTO(
                null,
                "Rua de Baixo",
                "29144-040",
                "24",
                "Casa",
                "Bairro Oriente",
                "ES",
                "Cariacica",
                TipoEndereco.ENTREGA,
                null,
                null
        );

        List<EnderecoCadastrarDTO> enderecoCadastrarDTOS = new ArrayList<>();
        enderecoCadastrarDTOS.add(enderecoCadastrarDTO1);
        enderecoCadastrarDTOS.add(enderecoCadastrarDTO2);

        PessoaFisicaCadastroDTO pessoaFisicaCadastroDTO = new PessoaFisicaCadastroDTO(
                null,
                "LODOVIKO TESTE POSTMAN10",
                "lodoviko@hotmail.com",
                "9991111111",
                "FISICA",
                "61392162068",
                dtNascimento,
                2002L,
                enderecoCadastrarDTOS
         );

        PessoaFisicaExibirDTO pessoaFisicaExibirDTO = pessoaController.salvarPF(pessoaFisicaCadastroDTO).getBody();

        assertEquals(true, pessoaFisicaExibirDTO.id() > 0);

        for(EnderecoExibirDTO endereco : pessoaFisicaExibirDTO.enderecos()) {
            assertEquals(true, endereco.id() > 0);
        }

        assertEquals(2, pessoaFisicaExibirDTO.enderecos().size());
    }

}