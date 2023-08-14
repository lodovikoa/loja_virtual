package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Acesso;
import br.com.lodoviko.loja_virtual_mentoria.repository.AcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AcessoService {

    private AcessoRepository acessoRepository;

    public Acesso save(Acesso acesso) throws ExceptionMentoriaJava {

        /* Verificar se já existe Acesso cadastrado com a mesma descrição */
        List<Acesso> acessos = acessoRepository.buscarAcessoDescUnico(acesso.getDescricao().toUpperCase().trim());

        if(!acessos.isEmpty()) {
            throw new ExceptionMentoriaJava("Já existe no cadastro Acesso com a descrição: " + acesso.getDescricao().trim());
        }


        return acessoRepository.save(acesso);
    }

    public void excluir(Long id) {
        acessoRepository.deleteById(id);
    }

    public Acesso consultarPorID(Long id) throws ExceptionMentoriaJava {
        var acesso = acessoRepository.findById(id).orElse(null);

        if(acesso == null) {
            throw new ExceptionMentoriaJava("Não encontrou Acesso com código: " + id);
        }

        return acesso;
    }

    public List<Acesso> buscarPorDesc(String descricao) {
        return acessoRepository.buscarAcessoDescContem(descricao);
    }
}
