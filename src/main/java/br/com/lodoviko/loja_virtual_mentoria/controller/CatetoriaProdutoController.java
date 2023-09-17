package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CategoriaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.CategoriaProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoriaProduto")
public class CatetoriaProdutoController {

    private  final CategoriaProdutoService categoriaProdutoService;

    public CatetoriaProdutoController(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoriaProdutoDTO> cadastrarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        CategoriaProdutoDTO categoriaProdutoDTO = categoriaProdutoService.cadastrar(categoriaProduto);
        return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoDTO, HttpStatus.OK);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<CategoriaProdutoDTO> alterarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        CategoriaProdutoDTO categoriaProdutoDTO = categoriaProdutoService.alterar(categoriaProduto);
        return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoDTO, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> excluirCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
        categoriaProdutoService.excluir(categoriaProduto);
        return new ResponseEntity("Categoria do Produto foi removida", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoDTO>> listarCategoriaProduto() {
        var categoriaProdutoDTOs = categoriaProdutoService.listar().stream().map(CategoriaProdutoDTO::new);
        return new ResponseEntity<List<CategoriaProdutoDTO>>(categoriaProdutoDTOs.toList(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> buscar(@PathVariable Long id) {
        return categoriaProdutoService.buscarPorId(id)
                .map(CategoriaProdutoDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
