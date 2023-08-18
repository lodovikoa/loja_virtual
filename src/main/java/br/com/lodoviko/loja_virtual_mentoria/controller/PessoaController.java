package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Transactional
    @PostMapping(name = "/pj")
    public ResponseEntity<PessoaJuridica> salvarPJ(@RequestBody PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava {

        pessoaJuridica = pessoaService.salvarPJ(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }
}
