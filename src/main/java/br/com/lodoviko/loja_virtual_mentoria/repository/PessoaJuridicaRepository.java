package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    List<PessoaJuridica> findByCnpj(String cnpj);

    List<PessoaJuridica> findByInscEstadual(String inscEstadual);

    List<PessoaJuridica> findByRazaoSocialContaining(String razaoSocial);
}
