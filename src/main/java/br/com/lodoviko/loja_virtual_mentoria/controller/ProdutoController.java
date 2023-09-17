package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ProdutoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoExibirDTO> salvarProduto(@Valid @RequestBody ProdutoCadastrarDTO produtoCadastrarDTO) throws ExceptionMentoriaJava {
        var produto = produtoService.salvar(produtoCadastrarDTO);

        return new ResponseEntity<ProdutoExibirDTO>(produto.converterProdutoExibirDTO(), HttpStatus.OK);
    }
}
