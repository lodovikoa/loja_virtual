package br.com.lodoviko.loja_virtual_mentoria.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TipoPessoa {
    JURIDICA ("Jurídica"),
    JURIDICA_FORNECEDOR ("Jurídica e Fornecedor"),
    FISICA ("Física");

    private  String descricao;
}
