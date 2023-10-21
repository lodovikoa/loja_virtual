package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {

    @Modifying
    @Query("update NotaFiscalVenda n set n.vendaCompraLojaVirtual = null where n.vendaCompraLojaVirtual.id = ?1")
    void alterarTodasVendasParaPermitirExclusao(Long idVenda);

    @Modifying
    @Query("delete from NotaFiscalVenda n where n.vendaCompraLojaVirtual is null")
    void excluirTodasNotaFiscalSemVendaAssociada();
}
