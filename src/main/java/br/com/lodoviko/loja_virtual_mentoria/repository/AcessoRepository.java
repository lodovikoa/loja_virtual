package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    @Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")
    List<Acesso> buscarAcessoDescContem(String desc);

    @Query("select a from Acesso a where upper(trim(a.descricao)) like ?1")
    List<Acesso> buscarAcessoDescUnico(String descricao);
}
