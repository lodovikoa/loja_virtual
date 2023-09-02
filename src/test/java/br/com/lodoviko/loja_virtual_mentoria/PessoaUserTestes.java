package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.controller.PessoaController;
import br.com.lodoviko.loja_virtual_mentoria.enuns.TipoEndereco;
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

        PessoaJuridicaCadastrarDTO pessoaJuridicaCadastrarDTO = new PessoaJuridicaCadastrarDTO(
                null,
                "LODOVIKO TESTE POSTMAN10",
                "lodoviko@hotmail.com",
                "9991111111",
                "FISICA",
                "30256278000195",
                "" + Calendar.getInstance().getTimeInMillis(),
                "12345678",
                "EMPRESA FANTASIA",
                "EMPRESA LODOVIKO NEWS",
                "MICRO EMPRESA",
                2002L,
                enderecoCadastrarDTOS
        );

        PessoaJuridicaExibirDTO pessoaJuridicaExibirDTO = pessoaController.salvarPJ(pessoaJuridicaCadastrarDTO).getBody();

        assertEquals(true, pessoaJuridicaExibirDTO.id() > 0);

        for(EnderecoExibirDTO endereco : pessoaJuridicaExibirDTO.enderecos()) {
            assertEquals(true, endereco.id() > 0);
        }

        assertEquals(2, pessoaJuridicaExibirDTO.enderecos().size());
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

        PessoaFisicaCadastrarDTO pessoaFisicaCadastrarDTO = new PessoaFisicaCadastrarDTO(
                null,
                "LODOVIKO TESTE POSTMAN10",
                "lodoviko@hotmail.com",
                "9991111111",
                "FISICA",
                "66065670090",
                dtNascimento,
                2002L,
                enderecoCadastrarDTOS
         );

        PessoaFisicaExibirDTO pessoaFisicaExibirDTO = pessoaController.salvarPF(pessoaFisicaCadastrarDTO).getBody();

        assertEquals(true, pessoaFisicaExibirDTO.id() > 0);

        for(EnderecoExibirDTO endereco : pessoaFisicaExibirDTO.enderecos()) {
            assertEquals(true, endereco.id() > 0);
        }

        assertEquals(2, pessoaFisicaExibirDTO.enderecos().size());
    }

}