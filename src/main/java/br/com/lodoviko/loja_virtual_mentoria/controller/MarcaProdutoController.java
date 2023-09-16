package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.MarcaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.MarcaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.MarcaProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marcaProduto")
public class MarcaProdutoController {

    @Autowired
    private MarcaProdutoService marcaProdutoService;

    @Transactional
    @PostMapping
    public ResponseEntity<MarcaProdutoDTO> cadastrarMarcaProduto(@Valid @RequestBody MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        var mp = marcaProdutoService.cadastrar(marcaProduto);
        return new ResponseEntity<MarcaProdutoDTO>(mp.converterDTO(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<MarcaProdutoDTO> alterarMarcaProduto(@Valid @RequestBody MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        marcaProduto = marcaProdutoService.alterar(marcaProduto);
        return new ResponseEntity<MarcaProdutoDTO>(marcaProduto.converterDTO(),HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> excluirMarcaProduto(@RequestBody MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
        marcaProdutoService.excluir(marcaProduto);
        return new ResponseEntity("Marca do Produto excluida.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MarcaProdutoDTO>> listarMarcaProduto() {
        var marcaProdutoDTOs = marcaProdutoService.listar().stream().map(MarcaProdutoDTO::new);
        return new ResponseEntity<List<MarcaProdutoDTO>>(marcaProdutoDTOs.toList(), HttpStatus.OK);
    }
}
