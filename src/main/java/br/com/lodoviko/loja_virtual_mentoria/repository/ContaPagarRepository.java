package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {
    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndIdNot(String descricao, Long id);

    List<ContaPagar> findByDescricaoContaining(String descricao);
}
