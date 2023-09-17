package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CategoriaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.repository.CategoriaProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

    public CategoriaProdutoDTO cadastrar(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {

        if(categoriaProduto.getId() != null && categoriaProduto.getId() > 0) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Categoria do Produto!");
        }

        if(categoriaProdutoRepository.existsByNomeDesc(categoriaProduto.getNomeDesc())) {
            throw new ExceptionMentoriaJava("A Categoria " + categoriaProduto.getNomeDesc() + " já encontra-se cadastrada.");
        }

        validarCategoriaProduto(categoriaProduto);

        return categoriaProdutoRepository.save(categoriaProduto).converterCategoriaProdutoDTO();
    }


    public CategoriaProdutoDTO alterar(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        if(categoriaProduto.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID.");
        }

        if(!categoriaProdutoRepository.existsById(categoriaProduto.getId())) {
            throw new ExceptionMentoriaJava("Marca do Produto não foi encontrada.");
        }


        if(categoriaProdutoRepository.existsByNomeDescAndIdNot(categoriaProduto.getNomeDesc(), categoriaProduto.getId())) {
            throw new ExceptionMentoriaJava("A categoria " + categoriaProduto.getNomeDesc() + "já encontra-se cadastrada.");
        }

        this.validarCategoriaProduto(categoriaProduto);

        return categoriaProdutoRepository.save(categoriaProduto).converterCategoriaProdutoDTO();
    }

    public void excluir(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        if(categoriaProduto == null || categoriaProduto.getId() == null) {
            throw new ExceptionMentoriaJava("Faltlou informar a o ID da Categoria do Produto que será excluída");
        }

        if(!categoriaProdutoRepository.existsById(categoriaProduto.getId())) {
            throw new ExceptionMentoriaJava("Marca do Produto não foi encontrada.");
        }

        categoriaProdutoRepository.delete(categoriaProduto);
    }

    /* Validações para cadastrar e alterar */
    private void validarCategoriaProduto(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        if(categoriaProduto.getEmpresa() == null || categoriaProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }
    }

    public List<CategoriaProduto> listar() {
        return categoriaProdutoRepository.findAll();
    }

    public Optional<CategoriaProduto> buscarPorId(Long id) {
        return categoriaProdutoRepository.findById(id);
    }
}
