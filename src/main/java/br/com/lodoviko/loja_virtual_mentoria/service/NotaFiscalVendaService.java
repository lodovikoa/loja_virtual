package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalVenda;
import br.com.lodoviko.loja_virtual_mentoria.repository.NotaFiscalVendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotaFiscalVendaService {

    private final NotaFiscalVendaRepository notaFiscalVendaRepository;

    public List<NotaFiscalVenda> buscarNotaFiscalPorVenda(Long idVenda) {
        return notaFiscalVendaRepository.buscarNotaFiscalPorVenda(idVenda);
    }
}
