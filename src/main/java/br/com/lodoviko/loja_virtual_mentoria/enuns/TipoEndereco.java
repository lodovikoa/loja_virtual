package br.com.lodoviko.loja_virtual_mentoria.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TipoEndereco {
    COBRANCA("Cobrança"),
    ENTREGA("Entrega");

    private String descricao;

}
