package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.NotaFiscalCompraCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.NotaFiscalCompraExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.RelatorioProdutoAlertaEstoqueDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.RelatorioProdutoCompraNotaFiscalDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.NotaFiscalCompraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("notaFiscalCompra")
public class NotaFiscalCompraController {

    private final NotaFiscalCompraService notaFiscalCompraService;

    // Cadastrar
    @Transactional
    @PostMapping
    public ResponseEntity<NotaFiscalCompraExibirDTO> cadastrarNotaFiscalCompra(@Valid @RequestBody NotaFiscalCompraCadastrarDTO notaFiscalCompraDTO) throws ExceptionMentoriaJava {
        var retorno = notaFiscalCompraService.cadastrar(notaFiscalCompraDTO.converterDTO());

        return new ResponseEntity<NotaFiscalCompraExibirDTO>(retorno.converterDTO(), HttpStatus.OK);
    }

    // Alterar
    @Transactional
    @PutMapping
    public ResponseEntity<NotaFiscalCompraExibirDTO> alterarNotaFiscalCompra(@Valid @RequestBody NotaFiscalCompraCadastrarDTO notaFiscalCompraDTO) throws ExceptionMentoriaJava {
        var retorno = notaFiscalCompraService.alterar(notaFiscalCompraDTO.converterDTO());
        return  new ResponseEntity<NotaFiscalCompraExibirDTO>(retorno.converterDTO(),HttpStatus.OK);
    }

    // Excluir
    @Transactional
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirNotaFiscalCompra(@PathVariable("id") Long id) throws ExceptionMentoriaJava {
        notaFiscalCompraService.excluir(id);
        return new ResponseEntity<>("Nota Fiscal de Compra excluida", HttpStatus.OK);
    }

    // Listar
    @GetMapping
    public ResponseEntity<List<NotaFiscalCompraExibirDTO>> listarNotaFiscalCompra() {
        var retorno = notaFiscalCompraService.listar().stream().map(NotaFiscalCompraExibirDTO::new);
        return ResponseEntity.ok(retorno.toList());
    }

    // Consultar por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<NotaFiscalCompraExibirDTO> buscarPorId(@PathVariable("id") Long id) throws ExceptionMentoriaJava {
        return notaFiscalCompraService.buscarPorId(id)
                .map(NotaFiscalCompraExibirDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Consultar por descrição
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<NotaFiscalCompraExibirDTO>> buscarPorDescricao(@PathVariable("descricao") String descricao) throws ExceptionMentoriaJava {
        var retorno = notaFiscalCompraService.buscarPorDescricao(descricao).stream().map(NotaFiscalCompraExibirDTO::new);
        return ResponseEntity.ok(retorno.toList());
    }

    @GetMapping("relatorioProdutoNFCompra")
    public ResponseEntity<List<RelatorioProdutoCompraNotaFiscalDTO>> relatorioProdutoCompraNotaFiscLal(
            @RequestParam(value = "nomeProduto", required = false) String nomeProduto,
            @RequestParam(value = "dataInicial", required = true) LocalDate dataInicial,
            @RequestParam(value = "dataFinal", required = true) LocalDate dataFinal,
            @RequestParam(value = "codigoNota", required = false) Long codigoNota,
            @RequestParam(value = "codigoProduto", required = false) Long codigoProduto) throws ExceptionMentoriaJava {

        var retorno = notaFiscalCompraService.gerarRelatorioProdCompraNota(nomeProduto, dataInicial, dataFinal, codigoNota, codigoProduto);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("relatorioProdutoAlertaEstoque")
    public ResponseEntity<List<RelatorioProdutoAlertaEstoqueDTO>> relatorioProdutoAlertaEstoque(
            @RequestParam(value = "nomeProduto", required = false) String nomeProduto,
            @RequestParam(value = "dataInicial", required = true) LocalDate dataInicial,
            @RequestParam(value = "dataFinal", required = true) LocalDate dataFinal,
            @RequestParam(value = "codigoNota", required = false) Long codigoNota,
            @RequestParam(value = "codigoProduto", required = false) Long codigoProduto) throws ExceptionMentoriaJava {

        var retorno = notaFiscalCompraService.gerarRelatorioProdutoAlertaEstoque(nomeProduto, dataInicial, dataFinal, codigoNota, codigoProduto);
        return ResponseEntity.ok(retorno);
    }

}
