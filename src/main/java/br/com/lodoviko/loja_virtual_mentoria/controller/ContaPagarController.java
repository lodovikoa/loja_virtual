package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ContaPagarCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.ContaPagarExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.ContaPagarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("contaPagar")
public class ContaPagarController {

    private final ContaPagarService contaPagarService;

    @Transactional
    @PostMapping
    public ResponseEntity<ContaPagarExibirDTO> cadastrarContaPagar(@Valid @RequestBody ContaPagarCadastrarDTO contaPagarDTO) throws ExceptionMentoriaJava {
        var retorno = contaPagarService.salvar(contaPagarDTO.converterDTO());
        return new ResponseEntity<ContaPagarExibirDTO>(retorno.converterDTO(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ContaPagarExibirDTO> alterarContaPagar(@Valid @RequestBody ContaPagarCadastrarDTO contaPagarDTO) throws ExceptionMentoriaJava {
        var retorno = contaPagarService.alterar(contaPagarDTO.converterDTO());
        return new ResponseEntity<ContaPagarExibirDTO>(retorno.converterDTO(),HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> contaPagarExcluir(@PathVariable("id") Long id) throws ExceptionMentoriaJava {
        contaPagarService.excluir(id);
        return new ResponseEntity<>("Conta pagar removida", HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ContaPagarExibirDTO>> listarContaPagar() {
        var retorno = contaPagarService.listar().stream().map(ContaPagarExibirDTO:: new);

        return ResponseEntity.ok(retorno.toList());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<ContaPagarExibirDTO>> buscarPorDescricao(@PathVariable("descricao") String descricao) throws ExceptionMentoriaJava {
        var retorno = contaPagarService.buscarPorDescricao(descricao).stream().map(ContaPagarExibirDTO::new);
        return ResponseEntity.ok(retorno.toList());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ContaPagarExibirDTO> buscarPorId(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        return contaPagarService.buscarPorId(id)
                .map(ContaPagarExibirDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}