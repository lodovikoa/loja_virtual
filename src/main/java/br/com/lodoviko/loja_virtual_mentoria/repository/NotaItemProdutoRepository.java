package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaItemProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long> {

    @Query("select count(1) > 0  a from NotaItemProduto a where a.notaFiscalCompra.id = ?1")
    boolean findByExisteNotaFiscalCompra(Long idNotaFiscalCompra);

    // Excluir itens relacionados com a NotaFiscalCompra
    @Query(nativeQuery = true, value = "delete from tb_nota_item_produto where nota_fiscal_compra_id = ?1")
    void excluirItensNotaFiscalCompra(Long idNotaFiscalCompra);

    // Verificar se já existe o produto informado cadastrado para a NF
    // (notaItemProduto.getProduto.getId(), notaItemProduto.getNotaFiscalCompra.getId())
    boolean existsByProdutoAndNotaFiscalCompra(Produto produto, NotaFiscalCompra notaFiscalCompra);

    boolean existsByProdutoAndNotaFiscalCompraAndIdNot(Produto produto, NotaFiscalCompra notaFiscalCompra, Long id);

}
