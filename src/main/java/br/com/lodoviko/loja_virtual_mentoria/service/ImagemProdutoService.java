package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.ImagemProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import br.com.lodoviko.loja_virtual_mentoria.repository.ImagemProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ImagemProdutoService {

    private final ImagemProdutoRepository imagemProdutoRepository;

    /*
    * public List<ImagemProduto> ObterImagemPorProduto(Long idProduto)  -- OK
    * public void deleteImagemProduto(Long id) -- OK
    * public void deleteTodasImagensProduto(Long idProduto) -- OK
    * public ImagemProduto salvarImagemProduto(ImagemProduto imagemProduto)
    * */

    public List<ImagemProduto> obterImagensProduto(Long idProduto) {
        return imagemProdutoRepository.findByProduto(new Produto(idProduto));
    }

    public void deleteImagemProduto(Long id) throws ExceptionMentoriaJava {
        if(!imagemProdutoRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("Não foi localizado a Imagem com ID = " + id);
        }
        imagemProdutoRepository.deleteById(id);
    }

    public void deleteTodasImagensPorProduto(Long idProduto) throws ExceptionMentoriaJava {
        if(!imagemProdutoRepository.existsByProduto(new Produto(idProduto))) {
            throw new ExceptionMentoriaJava("Não há imagens para o produto indicado com ID = " + idProduto);
        }

        imagemProdutoRepository.deleteTodasImagensProduto(idProduto);
    }

    public ImagemProduto salvarImagemProduto(ImagemProduto imagemProduto) throws ExceptionMentoriaJava {
        if(imagemProduto.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro de Imagem do Produto.");
        }

        if(imagemProduto.getProduto() == null || imagemProduto.getProduto().getId() == null) {
            throw new ExceptionMentoriaJava("Não foi informado o produto.");
        }

        if(imagemProduto.getEmpresa() == null || imagemProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Não foi informado a Empresa.");
        }

        return imagemProdutoRepository.save(imagemProduto);
    }
}
