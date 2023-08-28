package br.com.lodoviko.loja_virtual_mentoria.repository;

import br.com.lodoviko.loja_virtual_mentoria.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    @Query(value = "select u from Usuario u where u.pessoa.id = ?1 or u.login = ?2")
    Usuario findUserByPessoa(Long id, String login);

    @Query(nativeQuery = true, value = "select * from tb_usuario u where u.data_atual_senha <= current_date() - ?1")
    List<Usuario> findUsuarioSenhaVencida(Integer qtdeDias);

    @Modifying
    @Query(nativeQuery = true, value = "insert into tb_usuario_acesso(usuario_id, acesso_id) values(?1, (select id from tb_acesso where descricao = ?2))")
    void insereAcessoUserPJ(Long usuarioId, String acesso);
}
