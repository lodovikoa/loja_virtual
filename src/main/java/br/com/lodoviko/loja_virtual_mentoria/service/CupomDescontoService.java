package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;
import br.com.lodoviko.loja_virtual_mentoria.repository.CupomDescontoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CupomDescontoService {

    private final CupomDescontoRepository cupomDescontoRepository;

    public List<CupomDesconto> listarCupomDescontoPorEmpresa(Long idEmpresa) {
        return cupomDescontoRepository.listarCupomDescontoPorEmpresa(idEmpresa);
    }
}
