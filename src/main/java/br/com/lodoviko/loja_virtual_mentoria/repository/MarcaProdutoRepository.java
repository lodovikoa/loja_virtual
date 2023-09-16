package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto, Long> {


    boolean existsByNomeDesc(String nomeDesc);
    boolean existsByNomeDescAndIdNot(String nomeDesc, Long id);
}
