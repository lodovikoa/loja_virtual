package br.com.lodoviko.loja_virtual_mentoria.model.dto;

public record ItemVendaLojaExibirDTO(
        Long id,
        Double quantidade,
        ProdutoExibirReduzidoDTO produto
) {
}
