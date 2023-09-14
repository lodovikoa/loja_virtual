package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CategoriaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.repository.CategoriaProdutoRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public CategoriaProduto salvar(CategoriaProdutoDTO categoriaProdutoDTO) throws ExceptionMentoriaJava {

        if(categoriaProdutoDTO.id() != null && categoriaProdutoDTO.id() > 0) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Categoria do Produto!");
        }
        if(categoriaProdutoDTO.idEmpresa() == null || categoriaProdutoDTO.idEmpresa() <= 0) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        Optional<PessoaJuridica> empresa = pessoaJuridicaRepository.findById(categoriaProdutoDTO.idEmpresa());
        if(!empresa.isPresent()) {
            throw new ExceptionMentoriaJava("Empresa informada não foi encontrada!");
        }

        CategoriaProduto categoriaProduto = new CategoriaProduto().converterDTOCategoriaProduto(categoriaProdutoDTO);
        categoriaProduto.setEmpresa(empresa.get());

        return categoriaProdutoRepository.save(categoriaProduto);
    }
}
