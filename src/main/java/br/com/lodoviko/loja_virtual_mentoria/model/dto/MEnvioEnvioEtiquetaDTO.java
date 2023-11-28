package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioEnvioEtiquetaDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String service;
    private String agency;

    private MEnvioFromEnvioEtiquetaDTO from = new MEnvioFromEnvioEtiquetaDTO();
    private MEnvioToEnvioEtiquetaDTO to = new MEnvioToEnvioEtiquetaDTO();
    private List<MEnvioProductsEnvioEtiquetaDTO> products =  new ArrayList<>();
    private List<MEnvioVolumesEnvioEtiquetaDTO> volumes = new ArrayList<>();

}
