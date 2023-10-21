package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.StatusRastreio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long> {

    @Modifying
    @Query("delete from StatusRastreio s where s.vendaCompraLojaVirtual.id = :idVenda")
    void excluirTodosRastreiosDeUmaVenda(@Param("idVenda") Long idVenda);
}
