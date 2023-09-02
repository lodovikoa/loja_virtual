package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    List<PessoaJuridica> findByCnpj(String cnpj);

    List<PessoaJuridica> findByInscEstadual(String inscEstadual);

    @Query("select p from PessoaJuridica p where p.id = ?1")
    public PessoaJuridica findByPrimaryKey(Long id);
}
