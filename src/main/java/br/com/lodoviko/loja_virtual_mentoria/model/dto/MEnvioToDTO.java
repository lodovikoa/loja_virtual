package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class MEnvioToDTO implements Serializable {
    private static final long serialVersionUID = -1231693536530867935L;

    private String postal_code;
}
