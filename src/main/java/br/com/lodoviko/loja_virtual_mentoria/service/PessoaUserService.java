package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.Usuario;
import br.com.lodoviko.loja_virtual_mentoria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PessoaUserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUserByPessoa(Long pessoaId, String pessoaEmail) {
        return usuarioRepository.findUserByPessoa(pessoaId, pessoaEmail);
    }

    public Usuario cadastrarUsuario(PessoaJuridica pessoaJuridica) {

        // Verificar se tem usuário cadastrado para a pessoa Juridica recem cadastrada, caso náo tenha fazer o cadastro do usuário
        Usuario usuario = this.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());

        if(usuario == null) {
            // Cadastrar novo usuário
            var senha = "" + Calendar.getInstance().getTime();
            var senhaCrip = new BCryptPasswordEncoder().encode(senha);

            usuario = new Usuario();
            usuario.setLogin(pessoaJuridica.getEmail());
            usuario.setSenha(senhaCrip);
            usuario.setPessoa(pessoaJuridica);
            usuario.setEmpresa(pessoaJuridica);
            usuario.setDataAtualSenha(Calendar.getInstance().getTime());

            usuario = usuarioRepository.save(usuario);

            usuarioRepository.insereAcessoUserPJ(usuario.getId());
        }

        return usuario;
    }
}
