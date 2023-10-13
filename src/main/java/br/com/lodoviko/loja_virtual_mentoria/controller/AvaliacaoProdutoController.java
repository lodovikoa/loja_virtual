package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.AvaliacaoProdutoCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.AvaliacaoProdutoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.AvaliacaoProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("avaliacaoProduto")
public class AvaliacaoProdutoController {

    private final AvaliacaoProdutoService avaliacaoProdutoService;

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<AvaliacaoProdutoExibirDTO> salvar(@Valid @RequestBody AvaliacaoProdutoCadastrarDTO avaliacaoProdutoCadastrarDTO) throws ExceptionMentoriaJava {
        var retorno = avaliacaoProdutoService.salvar(avaliacaoProdutoCadastrarDTO.converterDTO());
        return ResponseEntity.ok(new AvaliacaoProdutoExibirDTO(retorno));
    }

    @GetMapping("listarPorProduto/{idProduto}")
    public ResponseEntity<List<AvaliacaoProdutoExibirDTO>> buscarAvalidacaoPorProduto(@PathVariable Long idProduto) throws ExceptionMentoriaJava {
        var retorno = avaliacaoProdutoService.buscarAvaliacaoPorProduto(idProduto);
        return ResponseEntity.ok(retorno.stream().map(AvaliacaoProdutoExibirDTO :: new).toList());
    }

    @GetMapping("listarPorPessoa/{idPessoa}")
    public ResponseEntity<List<AvaliacaoProdutoExibirDTO>> buscarAvaliacaoPorPessoa(@PathVariable Long idPessoa) throws ExceptionMentoriaJava {
        var retorno = avaliacaoProdutoService.buscarAvaliacaoPorPessoa(idPessoa);
        return ResponseEntity.ok(retorno.stream().map(AvaliacaoProdutoExibirDTO :: new).toList());
    }

    @GetMapping("listarPorProdutoPessoa/{idProduto}/{idPessoa}")
    public ResponseEntity<List<AvaliacaoProdutoExibirDTO>> buscarAvaliacaoPorProdutoPessoa(@PathVariable Long idProduto, @PathVariable Long idPessoa) {
        var retorno = avaliacaoProdutoService.buscarAvalidacaoProdutoPessoa(idProduto, idPessoa);
        return ResponseEntity.ok(retorno.stream().map(AvaliacaoProdutoExibirDTO :: new).toList());
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws ExceptionMentoriaJava {
        avaliacaoProdutoService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
