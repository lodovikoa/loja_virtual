package br.com.lodoviko.loja_virtual_mentoria.integracao;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.MEnvioEmpresaTransporteDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TesteMelhorEnvio {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/calculate"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
                .header("User-Agent", "lodoviko@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"from\":{\"postal_code\":\"96020360\"},\"to\":{\"postal_code\":\"01018020\"},\"products\":[{\"id\":\"x\",\"width\":11,\"height\":17,\"length\":11,\"weight\":0.3,\"insurance_value\":10.1,\"quantity\":1},{\"id\":\"y\",\"width\":16,\"height\":25,\"length\":11,\"weight\":0.3,\"insurance_value\":55.05,\"quantity\":2},{\"id\":\"z\",\"width\":22,\"height\":30,\"length\":11,\"weight\":1,\"insurance_value\":30,\"quantity\":1}]}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JsonNode jsonNode = new ObjectMapper().readTree(response.body());
        Iterator<JsonNode> iterator = jsonNode.iterator();

        MEnvioEmpresaTransporteDTO empresaTransporteDTO = new MEnvioEmpresaTransporteDTO();
        List<MEnvioEmpresaTransporteDTO> empresaTransporteDTOS = new ArrayList<>();

        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            // System.out.println(node.get("name"));
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

        System.out.println(empresaTransporteDTOS.toString());
    }
}
