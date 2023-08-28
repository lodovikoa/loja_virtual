package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.Usuario;
import br.com.lodoviko.loja_virtual_mentoria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class TarefaAutomatizadaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSendService emailSendService;

    // @Scheduled(initialDelay = 2000, fixedDelay = 86400000) /* Processo roda a cada 24h */
    @Scheduled(cron = "0 48 19 * * *", zone = "America/Sao_Paulo") /* Processo roda todo dia as 11h */
    public void notificarUserTrocaSenha() throws MessagingException, UnsupportedEncodingException, InterruptedException {

        List<Usuario> usuarios = usuarioRepository.findUsuarioSenhaVencida(90);

        for(Usuario usuario: usuarios) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("Olá, ")
                    .append(usuario.getPessoa().getNome())
                    .append("!<br/>")
                    .append("Está na hora de trocar sua senha, já passou 90 dias de uso.")
                    .append("<br/>");

            emailSendService.enviarEmailHtml("Troca de senha", mensagem.toString(), usuario.getLogin());

            Thread.sleep(3000); // Dorme por 3 segundos
        }

    }
}
