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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
