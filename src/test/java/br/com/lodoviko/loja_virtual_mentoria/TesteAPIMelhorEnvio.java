package br.com.lodoviko.loja_virtual_mentoria;

import br.com.lodoviko.loja_virtual_mentoria.integracao.ApiTokenIntegracao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TesteAPIMelhorEnvio {

    public static void main(String[] args) throws IOException, InterruptedException {

        /* Insere as etiquetas do Frete */
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/cart"))
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
//                .header("User-Agent", "lodoviko@gmail.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"service\":3, \"agency\":49, \"from\":{ \"name\":\"Nome do remetente\", \"phone\":\"53984470102\", \"email\":\"contato@melhorenvio.com.br\", \"document\":\"16571478358\", \"company_document\":\"89794131000100\", \"state_register\":\"123456\", \"address\":\"Endereço do remetente\", \"complement\":\"Complemento\", \"number\":\"1\", \"district\":\"Bairro\", \"city\":\"São Paulo\", \"country_id\":\"BR\", \"postal_code\":\"01002001\", \"note\":\"observação\" }, \"to\":{ \"name\":\"Nome do destinatário\", \"phone\":\"53984470102\", \"email\":\"contato@melhorenvio.com.br\", \"document\":\"25404918047\", \"company_document\":\"07595604000177\", \"state_register\":\"123456\", \"address\":\"Endereço do destinatário\", \"complement\":\"Complemento\", \"number\":\"2\", \"district\":\"Bairro\", \"city\":\"Porto Alegre\", \"state_abbr\":\"RS\", \"country_id\":\"BR\", \"postal_code\":\"90570020\", \"note\":\"observação\" }, \"products\":[ { \"name\":\"Papel adesivo para etiquetas 1\", \"quantity\":3, \"unitary_value\":100.00 }, { \"name\":\"Papel adesivo para etiquetas 2\", \"quantity\":1, \"unitary_value\":700.00 } ], \"volumes\":[ { \"height\":15, \"width\":20, \"length\":10, \"weight\":3.5 } ], \"options\":{ \"insurance_value\":1000.00, \"receipt\":false, \"own_hand\":false, \"reverse\":false, \"non_commercial\":false, \"invoice\":{ \"key\":\"31190307586261000184550010000092481404848162\" }, \"platform\":\"Nome da Plataforma\", \"tags\":[ { \"tag\":\"Identificação do pedido na plataforma, exemplo: 1000007\", \"url\":\"Link direto para o pedido na plataforma, se possível, caso contrário pode ser passado o valor null\" } ] } }"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//
//        System.out.println(response.body());

        /* Faz a compra do Frete para a etiqueta */
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/checkout"))
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
//                .header("User-Agent", "lodoviko@gmail.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"orders\": [ \"9aaa4943-5fd9-4cfd-b74a-782893711f78\" ] }"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//
//        System.out.println(response.body());

        /* Gera as etiquetas */
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/generate"))
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
//                .header("User-Agent", "lodoviko@gmail.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{ \"orders\": [ \"9aa859f9-6401-4452-8db5-93a03c468868\" ] }"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//
//        System.out.println(response.body());
//
//    }

        /* Faz impressao das etiquetas */
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(ApiTokenIntegracao.URL_MELHOR_ENVIO_SANDBOX + "api/v2/me/shipment/print"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SANDBOX)
            .header("User-Agent", "lodoviko@gmail.com")
            .method("POST", HttpRequest.BodyPublishers.ofString("{ \"mode\": \"private\", \"orders\": [ \"9aa859f9-6401-4452-8db5-93a03c468868\" ] }"))
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());

}
}
