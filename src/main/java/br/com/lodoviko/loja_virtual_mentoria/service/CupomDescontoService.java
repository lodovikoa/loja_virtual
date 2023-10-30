package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.CupomDesconto;
import br.com.lodoviko.loja_virtual_mentoria.repository.CupomDescontoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CupomDescontoService {

    private final CupomDescontoRepository cupomDescontoRepository;

    public List<CupomDesconto> listarCupomDescontoPorEmpresa(Long idEmpresa) {
        return cupomDescontoRepository.listarCupomDescontoPorEmpresa(idEmpresa);
    }

    public Optional<CupomDesconto> buscarPorId(Long id) throws ExceptionMentoriaJava {
        if(!cupomDescontoRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID " + id + " não foi encontrado.");
        }
        return cupomDescontoRepository.findById(id);
    }

    public CupomDesconto cadastrar(CupomDesconto cupomDesconto) throws ExceptionMentoriaJava {
        if(cupomDesconto.getId() != null) {
            throw new ExceptionMentoriaJava("No cadastro, não informar o ID");
        }

        return cupomDescontoRepository.save(cupomDesconto);
    }

    public CupomDesconto alterar(CupomDesconto cupomDesconto) throws ExceptionMentoriaJava {
        if(cupomDesconto.getId() == null) {
            throw new ExceptionMentoriaJava("Na alteração o ID é obrigatório.");
        }

        if(!cupomDescontoRepository.existsById(cupomDesconto.getId())) {
            throw new ExceptionMentoriaJava("O Cupom com código ID " + cupomDesconto.getId() + " não foi encontrado.");
        }

        return cupomDescontoRepository.save(cupomDesconto);
    }

    public void excluir(Long id) throws ExceptionMentoriaJava {
        if(!cupomDescontoRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID " + id + " não foi encontrado!");
        }

        cupomDescontoRepository.deleteById(id);
    }
}
