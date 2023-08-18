package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.controller.AcessoController;
import br.com.lodoviko.loja_virtual_mentoria.model.Acesso;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.repository.AcessoRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Profile("tst")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class AcessoTestes extends TestCase {

    @Autowired
    private AcessoController acessoController;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PessoaRepository pessoaRepository;

    /* Teste do end-point salvar 'Post' */
    @Test
    public void testRestApiCadastroAcesso() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_COMPRADOR999");

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.post("/acesso")
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());

        /* Converte o retorno da API para um objeto de acesso */
        Acesso acessoRetorno = objectMapper
                .readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);

        assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());

        acessoRepository.deleteById(acessoRetorno.getId());
    }

    @Test
    public void testRestApiDeleteAcesso() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_COMPRADOR_TESTE_DELETE");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.delete("/acesso/" + acesso.getId()));

        System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
        System.out.println("Retorno do Status: " + retornoApi.andReturn().getResponse().getStatus());

        assertEquals("Acesso removido", retornoApi.andReturn().getResponse().getContentAsString());
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }

    @Test
    public void testRestApiConsultarAcessoPorID() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_TESTE_POR_ID");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.get("/acesso/" + acesso.getId())
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);

        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
        assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
    }


    @Test
    public void testRestApiConsultarAcessoPorDescricao() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_TESTE_POR_DESCRICAO");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.get("/acesso/descricao/TESTE_POR_DESCRICAO")
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        List<Acesso> retornoApiList = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), new TypeReference<List<Acesso>>() {});

        assertEquals(1, retornoApiList.size());
        assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());

        acessoRepository.deleteById(acesso.getId());
    }


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


    /* --------------------------------------------------------------------------------- */


}
