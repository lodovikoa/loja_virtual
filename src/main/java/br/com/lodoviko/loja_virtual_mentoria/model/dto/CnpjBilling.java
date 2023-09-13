package br.com.lodoviko.loja_virtual_mentoria.model.dto;

public record CnpjBilling( /* Informações sobre o uso de limites da consulta */
        boolean free, /* Indica se a consulta utilizou a API Gratuita */
        boolean database /* Indica se a consulta foi resolvida com dados do cache */
) {
}
