package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioToEnvioEtiquetaDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String document;
    private String company_document;
    private String state_register;
    private String address;
    private String complement;
    private String number;
    private String district;
    private String city;
    private String state_abbr;
    private String country_id;
    private String postal_code;
    private String note;
}
