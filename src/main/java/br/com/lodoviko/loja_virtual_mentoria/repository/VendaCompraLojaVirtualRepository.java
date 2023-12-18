package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long> {

    VendaCompraLojaVirtual findByIdAndExcluidoIsFalse(Long id);

    boolean existsByIdAndExcluidoIsTrue(Long id);

    boolean existsByIdAndExcluidoIsFalse(Long id);

    @Modifying
    @Query("update VendaCompraLojaVirtual v set v.excluido = true where v.id = :id")
    void excluirLogicamente(@Param("id") Long id);

    @Modifying
    @Query("update VendaCompraLojaVirtual v set v.excluido = false where v.id = :id")
    void reativarLogicamente(@Param("id") Long id);

    @Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
    List<VendaCompraLojaVirtual> listarVendasPorIdProduto(Long idProduto);

    @Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.produto.nome like %?1%")
    List<VendaCompraLojaVirtual> listarVendasPorNomeProduto(String nomeProduto);

    @Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.vendaCompraLojaVirtual.pessoa.nome like %?1%")
    List<VendaCompraLojaVirtual> listarVendasPorNomeCliente(String nomeCliente);

    @Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.vendaCompraLojaVirtual.pessoa.id = ?1")
    List<VendaCompraLojaVirtual> listarVendasPorIdCliente(Long idCliente);

    @Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.vendaCompraLojaVirtual.dataVenda between ?1 and ?2")
    ArrayList<VendaCompraLojaVirtual> listarPorDataVenda(Date dataVendaInicio, Date dataVendaFim);

    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "update tb_vd_cp_loja_virt set codigo_etiqueta = ?1 where id = ?2")
    void updateEtiqueta(String idEtiqueta, Long id);

    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "update tb_vd_cp_loja_virt set url_imprime_etiqueta = ?1 where id = ?2")
    void updateUrlEtiqueta(String urlEtiqueta, Long id);
}
