package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ImagemProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ImagemProdutoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.ImagemProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("imagemProduto")
public class ImagemProdutoController {

    private final ImagemProdutoService imagemProdutoService;

    @GetMapping("listarPorProduto/{idProduto}")
    public ResponseEntity<List<ImagemProdutoExibirDTO>> obterImagensProduto(@PathVariable Long idProduto) {
        var retorno = imagemProdutoService.obterImagensProduto(idProduto).stream().map(ImagemProdutoExibirDTO :: new);
        return ResponseEntity.ok(retorno.toList());
    }

    @Transactional
    @DeleteMapping("excluir/{id}")
    public ResponseEntity<Void> deleteImagemProduto(@PathVariable Long id) throws ExceptionMentoriaJava {
        imagemProdutoService.deleteImagemProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @DeleteMapping("excluirPorProduto/{idProduto}")
    public  ResponseEntity<Void> deleteTodasImagensPorProduto(@PathVariable Long idProduto) throws ExceptionMentoriaJava {
        imagemProdutoService.deleteTodasImagensPorProduto(idProduto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<ImagemProdutoExibirDTO> salvarImagemProduto(@Valid @RequestBody ImagemProdutoCadastrarDTO imagemProdutoCadastrarDTO) throws ExceptionMentoriaJava {
        var retorno = imagemProdutoService.salvarImagemProduto(imagemProdutoCadastrarDTO.converterDTO());
        return ResponseEntity.ok(retorno.converterDTO());
    }
}
