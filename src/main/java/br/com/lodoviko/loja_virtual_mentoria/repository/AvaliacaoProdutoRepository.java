package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.AvaliacaoProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {

    List<AvaliacaoProduto> findByProduto(Produto produto);
    List<AvaliacaoProduto> findByPessoa(PessoaFisica pessoa);
    List<AvaliacaoProduto> findByProdutoAndPessoa(Produto produto, PessoaFisica pessoa);
}
