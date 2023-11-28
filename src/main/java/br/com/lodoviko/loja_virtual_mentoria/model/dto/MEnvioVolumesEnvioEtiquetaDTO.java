package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioVolumesEnvioEtiquetaDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String height;
    private String width;
    private String length;
    private String weight;

}
