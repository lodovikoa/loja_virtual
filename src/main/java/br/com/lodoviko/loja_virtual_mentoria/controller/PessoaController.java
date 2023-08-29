package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaJuridica;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.EnderecoCompletoDTO;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.PessoaJuridicaCompletaDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Transactional
    @PostMapping(value = "/pj")
    public ResponseEntity<PessoaJuridicaCompletaDTO> salvarPJ(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava {

        pessoaJuridica = pessoaService.salvarPJ(pessoaJuridica);

        List<EnderecoCompletoDTO> enderecoCompletoDTOs = new ArrayList<>();
        for(Endereco endereco: pessoaJuridica.getEnderecos()) {
            enderecoCompletoDTOs.add(endereco.converterEnderecoCompletoDTO(endereco));
        }

        PessoaJuridicaCompletaDTO pessoaJuridicaCompletaDTO = pessoaJuridica.converterPessoaJuridicaCompletoDTO(pessoaJuridica, enderecoCompletoDTOs);

       return ResponseEntity.ok(pessoaJuridicaCompletaDTO);
    }
}
