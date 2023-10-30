package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.CupomDescontoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.CupomDescontoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("cupomDesconto")
public class CupomDescontoController {

    private final CupomDescontoService cupomDescontoService;

    @GetMapping("listar/idEmpresa/{idEmpresa}")
    public ResponseEntity<List<CupomDescontoExibirDTO>> listarCupomDescontoPorEmpresa(@PathVariable Long idEmpresa) {
        var retorno = cupomDescontoService.listarCupomDescontoPorEmpresa(idEmpresa);

        return ResponseEntity.ok(retorno.stream().map(CupomDescontoExibirDTO::new).toList());
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<CupomDescontoExibirDTO> buscarPorId(@PathVariable Long id) throws ExceptionMentoriaJava {
        return cupomDescontoService.buscarPorId(id)
                .map(CupomDescontoExibirDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<CupomDescontoExibirDTO> cadastrar(@RequestBody @Valid CupomDesconto cupomDesconto) throws ExceptionMentoriaJava {
        var retorno = cupomDescontoService.cadastrar(cupomDesconto);
        return ResponseEntity.ok(retorno.converteDTO());
    }

    @Transactional
    @PutMapping("alterar")
    public ResponseEntity<CupomDescontoExibirDTO> alterar(@RequestBody @Valid CupomDesconto cupomDesconto) throws ExceptionMentoriaJava {
        var retorno = cupomDescontoService.alterar(cupomDesconto);
        return ResponseEntity.ok(retorno.converteDTO());
    }

    @Transactional
    @DeleteMapping("excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws ExceptionMentoriaJava {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
