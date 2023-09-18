package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(ProdutoCadastrarDTO dadosDTO) throws ExceptionMentoriaJava {

        if(dadosDTO.id() != null ) {
            throw new ExceptionMentoriaJava("Não informar o ID do produto no cadastro.");
        }

        if(dadosDTO.empresa() == null || dadosDTO.empresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa.");
        }

        if(produtoRepository.existsByNomeAndEmpresa(dadosDTO.nome().trim(), dadosDTO.empresa())) {
            throw new ExceptionMentoriaJava("Já existe um produto com nome " + dadosDTO.nome() + " cadastrado.");
        }

        return produtoRepository.save(new Produto(dadosDTO));
    }
}
