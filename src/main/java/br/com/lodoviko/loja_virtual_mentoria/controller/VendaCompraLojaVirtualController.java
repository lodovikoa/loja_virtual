package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.VendaCompraLojaVirtualCadastroDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.VendaCompraLojaVirtualExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.VendaCompraLojaVirtualService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("vendaLoja")
public class VendaCompraLojaVirtualController {

    private final VendaCompraLojaVirtualService vendaCompraLojaVirtualService;

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<VendaCompraLojaVirtualExibirDTO> salvar(@Valid @RequestBody VendaCompraLojaVirtualCadastroDTO vendaCompraLojaVirtualCadastroDTO) throws ExceptionMentoriaJava {
        var retorno = vendaCompraLojaVirtualService.salvar(vendaCompraLojaVirtualCadastroDTO.converterDTO());
        return ResponseEntity.ok(new VendaCompraLojaVirtualExibirDTO(retorno));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<VendaCompraLojaVirtualExibirDTO> consultarPorId(@PathVariable Long id) throws ExceptionMentoriaJava {
        var retorno = vendaCompraLojaVirtualService.consultarPorId(id);
        return retorno != null? ResponseEntity.ok(new VendaCompraLojaVirtualExibirDTO(retorno)) : ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("excluir/{idVenda}")
    public ResponseEntity<Void> excluirVendaTotal(@PathVariable Long idVenda) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.excluirVendaTotal(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PutMapping("excluirLogicamente/{idVenda}")
    public ResponseEntity<Void> excluirVendaLogicamente(@PathVariable Long idVenda) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.excluirLogicamente(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PutMapping("reativarLogicamente/{idVenda}")
    public ResponseEntity<Void> reativarVendaLogicamente(@PathVariable Long idVenda ) throws ExceptionMentoriaJava {
        vendaCompraLojaVirtualService.reativarLogicamente(idVenda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("listarVendaDinamica/{tipoConsulta}/{valor}")
    public ResponseEntity<List<VendaCompraLojaVirtualExibirDTO>> listarVendasDinamica(@PathVariable String tipoConsulta, @PathVariable String valor) throws ExceptionMentoriaJava {
        var retorno = vendaCompraLojaVirtualService.listarVendasPorProduto(tipoConsulta, valor);

        return ResponseEntity.ok(retorno.stream().map(VendaCompraLojaVirtualExibirDTO :: new).toList());
    }
}
