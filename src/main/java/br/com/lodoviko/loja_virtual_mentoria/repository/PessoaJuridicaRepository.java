package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    List<PessoaJuridica> findByCnpj(String cnpj);
}
