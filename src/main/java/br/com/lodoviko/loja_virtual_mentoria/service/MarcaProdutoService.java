package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;
import br.com.lodoviko.loja_virtual_mentoria.repository.MarcaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaProdutoService {

    @Autowired
    private MarcaProdutoRepository marcaProdutoRepository;

    public MarcaProduto cadastrar(MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        if(marcaProduto.getId() != null && marcaProduto.getId() > 0) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Marca do Produto.");
        }

        if(marcaProdutoRepository.existsByNomeDesc(marcaProduto.getNomeDesc())) {
            throw new ExceptionMentoriaJava("Marca do produto " + marcaProduto.getNomeDesc() + " já encontra-se cadastrada.");
        }

        this.validarMarcaProduto(marcaProduto);

        return marcaProdutoRepository.save(marcaProduto);
    }

    public MarcaProduto alterar(MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        if(marcaProduto.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID da Marca do Produto!");
        }

        if(!marcaProdutoRepository.existsById(marcaProduto.getId())) {
            throw new ExceptionMentoriaJava("Marca do Produto não foi encontrada.");
        }

        if(marcaProdutoRepository.existsByNomeDescAndIdNot(marcaProduto.getNomeDesc(), marcaProduto.getId())) {
            throw new ExceptionMentoriaJava("A marca do produto " + marcaProduto.getNomeDesc() + " já encontra-se cadastrada.");
        }

        this.validarMarcaProduto(marcaProduto);

        return  marcaProdutoRepository.save(marcaProduto);
    }

    public void excluir(MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        if(marcaProduto == null || marcaProduto.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID da Marca do Produto.");
        }

        if(!marcaProdutoRepository.existsById(marcaProduto.getId())) {
            throw new ExceptionMentoriaJava("Marca do Produto não foi encontrada.");
        }

        marcaProdutoRepository.delete(marcaProduto);
    }

    public List<MarcaProduto> listar() {
        return marcaProdutoRepository.findAll();
    }

    public Optional<MarcaProduto> buscarPorId(Long id) {
        return marcaProdutoRepository.findById(id);
    }

    private void validarMarcaProduto(MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        if(marcaProduto.getEmpresa() == null || marcaProduto.getEmpresa().getId() == null ) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa.");
        }
    }
}

