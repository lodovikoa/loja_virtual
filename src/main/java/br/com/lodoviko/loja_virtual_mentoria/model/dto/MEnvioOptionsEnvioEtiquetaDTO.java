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
public class MEnvioOptionsEnvioEtiquetaDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String insurance_value;
    private Boolean receipt;
    private Boolean own_hand;
    private Boolean reverse;
    private String non_comercial;
    private MEnvioInvoiceEnvioDTO invoice = new MEnvioInvoiceEnvioDTO();
    private String platform;
    List<MEnvioTagsEnvioDTO> tags = new ArrayList<>();
}
