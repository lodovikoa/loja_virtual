package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.StatusRastreio;
import br.com.lodoviko.loja_virtual_mentoria.repository.StatusRastreioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StatusRastreioService {

    private final StatusRastreioRepository statusRastreioRepository;

    public List<StatusRastreio> listarRastreioVenda(Long idVenda) throws ExceptionMentoriaJava {

        List<StatusRastreio> statusRastreios = statusRastreioRepository.listarEstatusPorVenda(idVenda);

        if(statusRastreios.isEmpty()) {
            throw new ExceptionMentoriaJava("Não há registro de rastreio para venda informada com código = " + idVenda);
        }

        return statusRastreios;
    }
}
