package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioProductsEnvioEtiquetaDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String name;
    private String quantity;
    private String unitary_value;
}
