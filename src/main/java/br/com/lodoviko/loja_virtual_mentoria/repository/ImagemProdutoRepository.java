package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.ImagemProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {

    List<ImagemProduto> findByProduto(Produto produto);

    @Modifying
    @Query(nativeQuery = true, value = "delete from tb_imagem_produto where produto_id = ?1")
    void deleteTodasImagensProduto(Long idProduto);

    boolean existsByProduto(Produto produto);

}
