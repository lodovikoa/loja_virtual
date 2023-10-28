package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.NotaFiscalVendaExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.NotaFiscalVendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("notaFiscalVenda")
public class NotaFiscalVendaController {

    private NotaFiscalVendaService notaFiscalVendaService;
    @GetMapping("idVenda/{idVenda}")
    public ResponseEntity<List<NotaFiscalVendaExibirDTO>> buscarNotaFiscalVendaPorProduto(@PathVariable Long idVenda) {
        var retorno = notaFiscalVendaService.buscarNotaFiscalPorVenda(idVenda);
        return ResponseEntity.ok(retorno.stream().map(NotaFiscalVendaExibirDTO::new).toList());
    }
}
