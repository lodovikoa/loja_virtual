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

    @Autowired
    private EmailSendService emailSendService;

    public Usuario findUserByPessoa(Long pessoaId, String pessoaEmail) {
        return usuarioRepository.findUserByPessoa(pessoaId, pessoaEmail);
    }

    public Usuario cadastrarUsuario(PessoaJuridica pessoaJuridica) {

        // Verificar se tem usuário cadastrado para a pessoa Juridica recem cadastrada, caso náo tenha fazer o cadastro do usuário
        Usuario usuario = this.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());

        if(usuario == null) {
            // Cadastrar novo usuário
            var senha = "" + Calendar.getInstance().getTimeInMillis();
            var senhaCrip = new BCryptPasswordEncoder().encode(senha);

            System.out.println("Senha: " + senha);

            usuario = new Usuario();
            usuario.setLogin(pessoaJuridica.getEmail());
            usuario.setSenha(senhaCrip);
            usuario.setPessoa(pessoaJuridica);
            usuario.setEmpresa(pessoaJuridica);
            usuario.setDataAtualSenha(Calendar.getInstance().getTime());

            usuario = usuarioRepository.save(usuario);

            usuarioRepository.insereAcessoUserPJ(usuario.getId(), "ROLE_USER");
            usuarioRepository.insereAcessoUserPJ(usuario.getId(), "ROLE_ADMIN");

            StringBuilder mensagemHtml = new StringBuilder();

            mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a Loja Virtual</b><br/>");
            mensagemHtml.append("<b>Login: </b>" + pessoaJuridica.getEmail() + "<br/>");
            mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
            mensagemHtml.append("Obrigado!");

            try {
                emailSendService.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString(), pessoaJuridica.getEmail());
            } catch(Exception e) {
                e.printStackTrace();
            }


        }

        return usuario;
    }
}
