package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaFisicaExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaCadastrarDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

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
