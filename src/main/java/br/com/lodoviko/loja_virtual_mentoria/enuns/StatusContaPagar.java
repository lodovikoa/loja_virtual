package br.com.lodoviko.loja_virtual_mentoria.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum StatusContaPagar {

    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    QUITADA("Quitada"),
    ALUGUEL("Aluguel"),
    FUNCIONARIO("Funcionário"),
    NEGOCIADA("Renegociada");

    private String descricao;
}
