package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.AcessoEndPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoEndPointRepository extends JpaRepository<AcessoEndPoint, Long> {

    @Query("select a from AcessoEndPoint a where a.nomeEndPoint = ?1")
    AcessoEndPoint findByNomeEndPoint(String nomeEndPoint);
}
