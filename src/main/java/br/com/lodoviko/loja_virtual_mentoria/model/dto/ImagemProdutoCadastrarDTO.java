package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.ImagemProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import jakarta.validation.constraints.NotNull;

public record ImagemProdutoCadastrarDTO(
        Long id,

        @NotNull(message = "Faltou informar a Imagem Original!")
        String imagemOriginal,

        @NotNull(message = "Faltou informar a Imagem Miniatura")
        String imagemMiniatura,

        @NotNull(message = "Faltou informar o Produto")
        Produto produto,

        @NotNull(message = "Faltou informa a Empresa")
        PessoaJuridica empresa
) {
   public ImagemProduto converterDTO() {
       return new ImagemProduto(
         this.id,
         this.imagemOriginal,
         this.imagemMiniatura,
         this.produto,
         this.empresa
       );
   }

}
