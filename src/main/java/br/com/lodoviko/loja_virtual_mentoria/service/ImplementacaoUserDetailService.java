package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.Usuario;
import br.com.lodoviko.loja_virtual_mentoria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplementacaoUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado!");
        }
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }
}
