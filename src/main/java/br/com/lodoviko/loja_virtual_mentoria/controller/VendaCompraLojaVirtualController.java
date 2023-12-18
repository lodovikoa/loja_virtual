package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.integracao.ApiTokenIntegracao;
import br.com.lodoviko.loja_virtual_mentoria.model.ItemVendaLoja;
import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.*;
import br.com.lodoviko.loja_virtual_mentoria.service.VendaCompraLojaVirtualService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("vendaLoja")
public class VendaCompraLojaVirtualController {

    private final VendaCompraLojaVirtualService vendaCompraLojaVirtualService;

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<VendaCompraLojaVirtualExibirDTO> salvar(@Valid @RequestBody VendaCompraLojaVirtualCadastroDTO vendaCompraLojaVirtualCadastroDTO) throws ExceptionMentoriaJava, MessagingException, UnsupportedEncodingException {
        var retorno = vendaCompraLojaVirtualService.salvar(vendaCompraLojaVirtualCadastroDTO.converterDTO());
        return ResponseEntity.ok(new VendaCompraLojaVirtualExibirDTO(retorno));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<VendaCompraLojaVirtualExibirDTO> consultarPorId(@PathVariable Long id) throws ExceptionMentoriaJava {
        var retorno = vendaCompraLojaVirtualService.consultarPorId(id);
        return retorno != null? ResponseEntity.ok(new VendaCompraLojaVirtualExibirDTO(retorno)) : ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("excluir/{idVenda}")
    public ResponseEntity<Void> excluirVendaTotal(@PathVariable Long idVenda) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.excluirVendaTotal(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PutMapping("excluirLogicamente/{idVenda}")
    public ResponseEntity<Void> excluirVendaLogicamente(@PathVariable Long idVenda) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.excluirLogicamente(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PutMapping("reativarLogicamente/{idVenda}")
    public ResponseEntity<Void> reativarVendaLogicamente(@PathVariable Long idVenda ) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.reativarLogicamente(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("listarVendaDinamica")
    public ResponseEntity<List<VendaCompraLojaVirtualExibirDTO>> listarVendasDinamica(
            @RequestParam(value = "idProduto", required = false, defaultValue = "-1") int idProduto,
            @RequestParam(value = "nomeProduto", required = false) String nomeProduto,
            @RequestParam(value = "nomeCliente", required = false) String nomeCliente,
            @RequestParam(value = "idCliente", required = false) Long idCliente,
            @RequestParam(value = "dataVendaInicio", required = false) Date dataVendaInicio,
            @RequestParam(value = "dataVendaFim", required = false) Date dataVendaFim) throws ExceptionMentoriaJava {
        var retorno = vendaCompraLojaVirtualService.listarVendasPorProduto(idProduto, nomeProduto, nomeCliente, idCliente, dataVendaInicio, dataVendaFim);

        return ResponseEntity.ok(retorno.stream().map(VendaCompraLojaVirtualExibirDTO :: new).toList());
    }

    @GetMapping("relatorioStatusVenda")
    public ResponseEntity<List<RelatorioStatusVendaDTO>> relatorioStatusVenda(
            @RequestParam(value = "dataInicial", required = true) LocalDate dataInicial,
            @RequestParam(value = "dataFinal", required = true) LocalDate dataFinal,
            @RequestParam(value = "statusVenda", required = false) String statusVenda,
            @RequestParam(value = "nomeProduto", required = false) String nomeProduto,
            @RequestParam(value = "nomeCliente", required = false) String nomeCliente
            ) {

        var retorno = vendaCompraLojaVirtualService.gerarRelatorioStatusVenda(dataInicial, dataFinal, statusVenda, nomeProduto, nomeCliente);

        return ResponseEntity.ok(retorno);
    }

    @Transactional
    @PostMapping(value = "imprimeCompraEtiquetaFrete")
    public ResponseEntity<String> imprimeCompraEtiquetaFrete(@RequestBody Long idVenda) throws ExceptionMentoriaJava, IOException, InterruptedException {

        VendaCompraLojaVirtual compraLojaVirtual = vendaCompraLojaVirtualService.consultarPorId(idVenda);
        if(compraLojaVirtual == null) {
            return new ResponseEntity<String>("Venda não encontrada", HttpStatus.OK);
        }

        MEnvioEnvioEtiquetaDTO envioEnvioEtiquetaDTO = new MEnvioEnvioEtiquetaDTO();
        envioEnvioEtiquetaDTO.setService(compraLojaVirtual.getServicoTransportadora());
        envioEnvioEtiquetaDTO.setAgency("49");

        envioEnvioEtiquetaDTO.getFrom().setName(compraLojaVirtual.getEmpresa().getNome());
        envioEnvioEtiquetaDTO.getFrom().setPhone(compraLojaVirtual.getEmpresa().getTelefone());
        envioEnvioEtiquetaDTO.getFrom().setEmail(compraLojaVirtual.getEmpresa().getEmail());
        envioEnvioEtiquetaDTO.getFrom().setCompany_document(compraLojaVirtual.getEmpresa().getCnpj());
        envioEnvioEtiquetaDTO.getFrom().setState_register(compraLojaVirtual.getEmpresa().getInscEstadual());
        envioEnvioEtiquetaDTO.getFrom().setAddress(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getRuaLogra());
        envioEnvioEtiquetaDTO.getFrom().setComplement(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getComplemento());
        envioEnvioEtiquetaDTO.getFrom().setNumber(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getNumero());
        envioEnvioEtiquetaDTO.getFrom().setDistrict(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getBairro());
        envioEnvioEtiquetaDTO.getFrom().setCity(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getCidade());
        envioEnvioEtiquetaDTO.getFrom().setCountry_id("BR");
        envioEnvioEtiquetaDTO.getFrom().setPostal_code(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getCep());
        envioEnvioEtiquetaDTO.getFrom().setNote("Não há");

        envioEnvioEtiquetaDTO.getTo().setName(compraLojaVirtual.getPessoa().getNome());
        envioEnvioEtiquetaDTO.getTo().setPhone(compraLojaVirtual.getPessoa().getTelefone());
        envioEnvioEtiquetaDTO.getTo().setEmail(compraLojaVirtual.getPessoa().getEmail());
        envioEnvioEtiquetaDTO.getTo().setDocument(compraLojaVirtual.getPessoa().getCpf());
        envioEnvioEtiquetaDTO.getTo().setAddress(compraLojaVirtual.getPessoa().enderecoEntrega().getRuaLogra());
        envioEnvioEtiquetaDTO.getTo().setComplement(compraLojaVirtual.getPessoa().enderecoEntrega().getComplemento());
        envioEnvioEtiquetaDTO.getTo().setNumber(compraLojaVirtual.getPessoa().enderecoEntrega().getNumero());
        envioEnvioEtiquetaDTO.getTo().setDistrict(compraLojaVirtual.getPessoa().enderecoEntrega().getBairro());
        envioEnvioEtiquetaDTO.getTo().setCity(compraLojaVirtual.getPessoa().enderecoEntrega().getCidade());
        envioEnvioEtiquetaDTO.getTo().setState_abbr(compraLojaVirtual.getPessoa().enderecoEntrega().getUf());
        envioEnvioEtiquetaDTO.getTo().setCountry_id("BR");
        envioEnvioEtiquetaDTO.getTo().setPostal_code(compraLojaVirtual.getPessoa().enderecoEntrega().getCep());
        envioEnvioEtiquetaDTO.getTo().setNote("Não há");

        List<MEnvioProductsEnvioEtiquetaDTO> products = new ArrayList<>();
        for (ItemVendaLoja itemVendaLoja : compraLojaVirtual.getItensVendaLoja()) {
            MEnvioProductsEnvioEtiquetaDTO dto = new MEnvioProductsEnvioEtiquetaDTO();

            dto.setName(itemVendaLoja.getProduto().getNome());
            dto.setQuantity(itemVendaLoja.getQuantidade() + "");
            dto.setUnitary_value(itemVendaLoja.getProduto().getValorVenda() + "");

            products.add(dto);
        }

        envioEnvioEtiquetaDTO.setProducts(products);

        List<MEnvioVolumesEnvioEtiquetaDTO> volumes = new ArrayList<>();
        for (ItemVendaLoja itemVendaLoja : compraLojaVirtual.getItensVendaLoja()) {
            MEnvioVolumesEnvioEtiquetaDTO dto = new MEnvioVolumesEnvioEtiquetaDTO();

            dto.setHeight(itemVendaLoja.getProduto().getAltura() + "");
            dto.setLength(itemVendaLoja.getProduto().getProfundidade() + "");
            dto.setWeight(itemVendaLoja.getProduto().getPeso() + "");
            dto.setWidth(itemVendaLoja.getProduto().getLargura() + "");

            volumes.add(dto);
        }

        envioEnvioEtiquetaDTO.setVolumes(volumes);

        envioEnvioEtiquetaDTO.getOptions().setInsurance_value(compraLojaVirtual.getValorTotal() + "");
        envioEnvioEtiquetaDTO.getOptions().setReceipt("false");
        envioEnvioEtiquetaDTO.getOptions().setOwn_hand("false");
        envioEnvioEtiquetaDTO.getOptions().setReverse("false");
        envioEnvioEtiquetaDTO.getOptions().setNon_comercial("false");
        envioEnvioEtiquetaDTO.getOptions().getInvoice().setKey(compraLojaVirtual.getNotaFiscalVenda().getNumero());
        envioEnvioEtiquetaDTO.getOptions().setPlatform(compraLojaVirtual.getEmpresa().getNomeFantasia());

        MEnvioTagsEnvioDTO dtoTagEnvio = new MEnvioTagsEnvioDTO();
        dtoTagEnvio.setTag("Identificação do pedido na plataforma, exemplo: " + compraLojaVirtual.getId());
        dtoTagEnvio.setUrl(null);

        envioEnvioEtiquetaDTO.getOptions().getTags().add(dtoTagEnvio);

        // Converter Object em Json
        String jsonEnvio = new ObjectMapper().writeValueAsString(envioEnvioEtiquetaDTO);

        /* Insere as etiquetas do Frete */
        HttpRequest requestEtiqueta = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/cart"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonEnvio))
                .build();
        HttpResponse<String> responseEtiqueta = HttpClient.newHttpClient().send(requestEtiqueta, HttpResponse.BodyHandlers.ofString());

        // Tratar a resposta da API
        JsonNode jsonNode = new ObjectMapper().readTree(responseEtiqueta.body());
        Iterator<JsonNode> iterator = jsonNode.iterator();

        String idEtiqueta = "";
        while ((iterator.hasNext())) {
            JsonNode node = iterator.next();
            idEtiqueta = node.get("id").asText();
            break;
        }

        // Salvar código da Etiqueta
        vendaCompraLojaVirtualService.updateEtiqueta(idEtiqueta, compraLojaVirtual.getId());

        /* Faz a compra do Frete para a etiqueta */
        HttpRequest requestCompraFrete = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/checkout"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"orders\": [ \"" + idEtiqueta + "\" ] }"))
                .build();
        HttpResponse<String> responseCompraFrete = HttpClient.newHttpClient().send(requestCompraFrete, HttpResponse.BodyHandlers.ofString());

        if(responseCompraFrete.statusCode() != 200) {
            return new ResponseEntity<String>("Não foi possível realizar a compra da Etiqueta.", HttpStatus.OK);
        }

        /* Gera as etiquetas */
        HttpRequest requestGeraEtiqueta = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/generate"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"orders\": [ \"" + idEtiqueta + "\" ] }"))
                .build();
        HttpResponse<String> responseGeraEtiqueta = HttpClient.newHttpClient().send(requestGeraEtiqueta, HttpResponse.BodyHandlers.ofString());

        if(responseGeraEtiqueta.statusCode() != 200) {
            return new ResponseEntity<String>("Não foi possível gerar a Etiqueta.", HttpStatus.OK);
        }

        /* Faz impressao das etiquetas */
        HttpRequest requestImprimeEtiqueta = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/print"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"mode\": \"private\", \"orders\": [ \"" + idEtiqueta + "\" ] }"))
                .build();
        HttpResponse<String> responseImprimeEtiqueta = HttpClient.newHttpClient().send(requestImprimeEtiqueta, HttpResponse.BodyHandlers.ofString());

        if(responseImprimeEtiqueta.statusCode() != 200) {
            return new ResponseEntity<String>("Não foi possível imprimir a Etiqueta.", HttpStatus.OK);
        }

        String urlEtiqueta = responseImprimeEtiqueta.body();

        vendaCompraLojaVirtualService.updateUrlEtiqueta(urlEtiqueta, compraLojaVirtual.getId());


        return new ResponseEntity<String>("Sucesso", HttpStatus.OK);
    }


    @PostMapping(value = "consultarFrete")
    public ResponseEntity<List<MEnvioEmpresaTransporteDTO>> constarFrete(@RequestBody @Valid MEnvioConsultaFreteDTO consultaFreteDTO) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(consultaFreteDTO);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/calculate"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JsonNode jsonNode = new ObjectMapper().readTree(response.body());
        Iterator<JsonNode> iterator = jsonNode.iterator();

        MEnvioEmpresaTransporteDTO empresaTransporteDTO = new MEnvioEmpresaTransporteDTO();
        List<MEnvioEmpresaTransporteDTO> empresaTransporteDTOS = new ArrayList<>();

        while (iterator.hasNext()) {
            JsonNode node = iterator.next();

            if(node.get("id") != null) {
                empresaTransporteDTO.setId(node.get("id").asText());
            }

            if(node.get("name") != null) {
                empresaTransporteDTO.setNome(node.get("name").asText());
            }

            if(node.get("price") != null) {
                empresaTransporteDTO.setValor(node.get("price").asText());
            }

            if(node.get("company") != null) {
                empresaTransporteDTO.setEmpresa(node.get("company").get("name").asText());
                empresaTransporteDTO.setPicture(node.get("company").get("picture").asText());
            }

            if(node.get("error") != null) {
                empresaTransporteDTO.setError(node.get("error").asText());
            }

            if(empresaTransporteDTO.dadosOK()) {
                empresaTransporteDTOS.add(empresaTransporteDTO);
            }
            empresaTransporteDTO = new MEnvioEmpresaTransporteDTO();
        }

       // return new ResponseEntity<List<MEnvioEmpresaTransporteDTO>>(empresaTransporteDTOS,HttpStatus.OK);
        return ResponseEntity.ok(empresaTransporteDTOS);
    }
}
