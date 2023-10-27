package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.StatusRastreio;

public record StatusRastreioExibirDTO(
       Long id,
       String centroDistribuicao,
       String cidade,
       String estado,
       String status,
        Long  idVendaCompraLojaVirtual,
        Long idEmpresa
) {
    public StatusRastreioExibirDTO(StatusRastreio statusRastreio) {
        this(statusRastreio.getId(),
                statusRastreio.getCentroDistribuicao(),
                statusRastreio.getCidade(),
                statusRastreio.getEstado(),
                statusRastreio.getStatus(),
                statusRastreio.getVendaCompraLojaVirtual().getId(),
                statusRastreio.getEmpresa().getId()
        );
    }
}
