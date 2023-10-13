package br.com.lodoviko.loja_virtual_mentoria.model.dto;

import br.com.lodoviko.loja_virtual_mentoria.model.AvaliacaoProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoProdutoCadastrarDTO(
        Long id,
        @NotNull(message = "Descrição é de preenchimento obrigatório.")
        String descricao,

        @Min(value = 1, message = "Nota deve ser entre 1 e 10. Foi informado valor menor que 1")
        @Max(value = 10, message = "Nota deve ser entre 1 e 10. Foi informado valor maior que 10")
        @NotNull(message = "Nota é de preenchimento obrigatório")
        Integer nota,
        @NotNull(message = "Faltou informar a Pessoa que está avaliando o produto")
        PessoaFisica pessoa,
        @NotNull(message = "Faltou informar o Produto que está sob avaliação.")
        Produto produto,
        @NotNull(message = "Faltou informar a Empresa.")
        PessoaJuridica empresa
) {
    public AvaliacaoProduto converterDTO() {
        return new AvaliacaoProduto(
                this.id,
                this.descricao,
                this.nota,
                this.pessoa,
                this.produto,
                this.empresa
        );
    }
}
