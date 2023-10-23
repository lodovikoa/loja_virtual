package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
