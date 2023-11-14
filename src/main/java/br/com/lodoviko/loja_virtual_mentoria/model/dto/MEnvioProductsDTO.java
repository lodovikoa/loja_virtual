package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class MEnvioProductsDTO implements Serializable {
    private static final long serialVersionUID = 4402908460861209706L;

    private String id;
    private String width;
    private String height;
    private String length;
    private String weight;
    private String insurance_value;
    private String quantity;
}
