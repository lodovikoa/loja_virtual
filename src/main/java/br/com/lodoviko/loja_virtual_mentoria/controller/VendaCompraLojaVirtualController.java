package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.VendaCompraLojaVirtualCadastroDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.VendaCompraLojaVirtualExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.VendaCompraLojaVirtualService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        return vendaCompraLojaVirtualService.consultarPorId(id)
                .map(VendaCompraLojaVirtualExibirDTO :: new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
