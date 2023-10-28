package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.CupomDescontoExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.CupomDescontoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
