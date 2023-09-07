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

import java.util.List;

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

    @GetMapping(value = "/pj/cnpj/{dsCnpj}")
    public ResponseEntity<List<PessoaJuridicaExibirDTO>> findByCnpjPJ(@PathVariable("dsCnpj") String dsCnpj) {
        List<PessoaJuridicaExibirDTO> pessoaJuridicaExibirDTOS = pessoaService.findByCnpjPJ(dsCnpj);
        return ResponseEntity.ok(pessoaJuridicaExibirDTOS);
    }

    @GetMapping(value = "/pj/razaosocial/{dsRazaoSocial}")
    public ResponseEntity<List<PessoaJuridicaExibirDTO>> findByRazaoSocialPJ(@PathVariable("dsRazaoSocial") String dsRazaoSocial) {
        List<PessoaJuridicaExibirDTO> pessoaJuridicaExibirDTOS = pessoaService.findByRazaoSocialPJ(dsRazaoSocial);
        return ResponseEntity.ok(pessoaJuridicaExibirDTOS);
    }

    @Transactional
    @PostMapping(value = "/pf")
    public ResponseEntity<PessoaFisicaExibirDTO> salvarPF(@RequestBody @Valid PessoaFisicaCadastrarDTO pessoaFisicaCadastrarDTO) throws ExceptionMentoriaJava {

        var pf = pessoaService.salvarPF(pessoaFisicaCadastrarDTO);

        return ResponseEntity.ok(pf);
    }

    @GetMapping(value = "/pf/cpf/{dsCpf}")
    public ResponseEntity<List<PessoaFisicaExibirDTO>> findByCpfPF(@PathVariable("dsCpf") String dsCpf) {
        List<PessoaFisicaExibirDTO> pessoaFisicaExibirDTOS = pessoaService.findByCpfPF(dsCpf);
        return ResponseEntity.ok(pessoaFisicaExibirDTOS);
    }

    @GetMapping(value = "/pf/nome/{dsNome}")
    public ResponseEntity<List<PessoaFisicaExibirDTO>> findByNomePF(@PathVariable("dsNome") String dsNome) {
        List<PessoaFisicaExibirDTO> pessoaFisicaExibirDTOS = pessoaService.findByNomePF(dsNome);
        return ResponseEntity.ok(pessoaFisicaExibirDTOS);
    }
}
