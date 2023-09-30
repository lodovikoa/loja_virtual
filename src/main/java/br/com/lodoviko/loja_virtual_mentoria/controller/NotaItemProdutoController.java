package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.NotaItemProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.NotaItemProdutoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.NotaItemProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("notaItemProduto")
public class NotaItemProdutoController {

    private final NotaItemProdutoService notaItemProdutoService;

    @Transactional
    @PostMapping
    public ResponseEntity<NotaItemProdutoExibirDTO> cadastrarNotaItemProduto(@Valid @RequestBody NotaItemProdutoCadastrarDTO notaItemProdutoCadastrarDTO) throws ExceptionMentoriaJava {
        var retorno = notaItemProdutoService.salvar(notaItemProdutoCadastrarDTO.converterDTO());

        return ResponseEntity.ok(retorno.converterDTO());
    }

    @Transactional
    @PutMapping
    public ResponseEntity<NotaItemProdutoExibirDTO> alterarNotaItemProduto(@Valid @RequestBody NotaItemProdutoCadastrarDTO notaItemProdutoCadastrarDTO) throws ExceptionMentoriaJava {
        var retorno = notaItemProdutoService.alterar(notaItemProdutoCadastrarDTO.converterDTO());

        return ResponseEntity.ok(retorno.converterDTO());
    }

}
