package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.*;
import br.com.lodoviko.loja_virtual_mentoria.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping(value = "/consultarCep/{cep}")
    public ResponseEntity<CepDTO> consultarCep(@PathVariable("cep") String cep) {
        return new ResponseEntity<CepDTO>(pessoaService.consultaCep(cep), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/pj")
    public ResponseEntity<PessoaJuridicaExibirDTO> salvarPJ(@RequestBody @Valid PessoaJuridicaCadastrarDTO pessoaJuridicaCadastrarDTO) throws ExceptionMentoriaJava {

        var pj = pessoaService.salvarPJ(pessoaJuridicaCadastrarDTO);

       return ResponseEntity.ok(pj);
    }

    @Transactional
    @PostMapping(value = "/pf")
    public ResponseEntity<PessoaFisicaExibirDTO> salvarPF(@RequestBody @Valid PessoaFisicaCadastrarDTO pessoaFisicaCadastrarDTO) throws ExceptionMentoriaJava {

        var pf = pessoaService.salvarPF(pessoaFisicaCadastrarDTO);

        return ResponseEntity.ok(pf);
    }
}
