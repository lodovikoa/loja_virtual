package br.com.lodoviko.loja_virtual_mentoria.model.dto;

public record CnpjQsaDTO( /* Quadro Societário */
        String nome, /* Nome do Sócio */
        String qual, /* Qualificação do Sócio */
        String pais_origem, /* País de Origem do Sócio */
        String nome_rep_legal, /* Nome do Representante Legal */
        String qual_rep_legal /* Nome do Representante Legal */) {
}
