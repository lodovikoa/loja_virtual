package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioConsultaFreteDTO implements Serializable {
    private static final long serialVersionUID = 7166540413286530002L;

    private MEnvioFromDTO from;
    private MEnvioToDTO to;
    private List<MEnvioProductsDTO> products;
}
