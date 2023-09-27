package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, Long> {

    boolean existsById(Long id);
    boolean existsByDescricaoObsAndIdNot(String descricao, Long id);
    List<NotaFiscalCompra> findByDescricaoObsContaining(String descricao);
    List<NotaFiscalCompra> findByPessoa(PessoaJuridica pessoa);
    List<NotaFiscalCompra> findByContaPagar(ContaPagar contaPagar);
    List<NotaFiscalCompra> findByEmpresa(PessoaJuridica empresa);
}
