package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.dto.StatusRastreioExibirDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.StatusRastreioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("statusRastreio")
public class StatusRastreioController {

    private final StatusRastreioService statusRastreioService;

    @GetMapping("listar")
    public ResponseEntity<List<StatusRastreioExibirDTO>> listarStatusPorVenda(@RequestParam(value = "idVenda") Long idVenda) throws ExceptionMentoriaJava {
        var retorno = statusRastreioService.listarRastreioVenda(idVenda);

        return ResponseEntity.ok(retorno.stream().map(StatusRastreioExibirDTO :: new).toList());
    }
}
