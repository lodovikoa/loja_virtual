package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
}
