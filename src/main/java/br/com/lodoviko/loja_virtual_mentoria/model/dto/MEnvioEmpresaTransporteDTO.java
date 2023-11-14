package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class MEnvioEmpresaTransporteDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String valor;
    private String empresa;
    private String picture;
    private String error;


    public boolean dadosOK() {
        if(error != null) {
            return false;
        }
        if(id != null && nome != null && valor != null && empresa != null && picture != null) {
            return true;
        }
        return false;
    }
}
