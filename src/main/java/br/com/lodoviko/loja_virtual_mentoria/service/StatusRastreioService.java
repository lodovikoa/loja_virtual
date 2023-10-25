package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.repository.StatusRastreioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StatusRastreioService {

    private final StatusRastreioRepository statusRastreioRepository;

    /*
    *  Fazer consulta de rastreio de uma venda
    *  public List<StatusRastreio> listarRastreioVenda(Long idVenda)
    * */
}
