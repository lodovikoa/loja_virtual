package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CategoriaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.repository.CategoriaProdutoRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public CategoriaProdutoDTO salvar(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {

        if(categoriaProduto.getId() != null && categoriaProduto.getId() > 0) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Categoria do Produto!");
        }
        if(categoriaProduto.getEmpresa() == null || categoriaProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        return categoriaProdutoRepository.save(categoriaProduto).converterCategoriaProdutoDTO();
    }
}
