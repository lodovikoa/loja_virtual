package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {

    @Query(value = "from CupomDesconto c where c.empresa.id = ?1")
    List<CupomDesconto> listarCupomDescontoPorEmpresa(Long idEmpresa);

    boolean existsById(Long id);
}
