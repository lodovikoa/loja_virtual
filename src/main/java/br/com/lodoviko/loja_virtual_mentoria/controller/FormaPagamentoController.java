package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.FormaPagamento;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.FormaPagamentoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("formaPagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<FormaPagamentoExibirDTO> cadastrar(@Valid @RequestBody FormaPagamento formaPagamento) throws ExceptionMentoriaJava {
        var retorno = formaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.ok(new FormaPagamentoExibirDTO(retorno));
    }

    @GetMapping("listar/idEmpresa/{idEmpresa}")
    public ResponseEntity<List<FormaPagamentoExibirDTO>> listarFormasPagamentoPorEmpresa(@PathVariable Long idEmpresa) {
        var retorno = formaPagamentoService.listarFormasPagamentoPorEmpresa(idEmpresa);

        return ResponseEntity.ok(retorno.stream().map(FormaPagamentoExibirDTO::new).toList());
    }
}
