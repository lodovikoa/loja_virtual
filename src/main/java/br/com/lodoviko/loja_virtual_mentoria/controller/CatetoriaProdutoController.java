package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CategoriaProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CategoriaProdutoDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoriaProduto")
public class CatetoriaProdutoController {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @Transactional
    @PostMapping
    public ResponseEntity<CategoriaProdutoDTO> salvarCategoriaProduto(@RequestBody CategoriaProdutoDTO categoriaProdutoDTO) throws ExceptionMentoriaJava {
        CategoriaProduto categoriaProdutoSalva = categoriaProdutoService.salvar(categoriaProdutoDTO);
        return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoSalva.converterCategoriaProdutoDTO(), HttpStatus.OK);
    }
}
