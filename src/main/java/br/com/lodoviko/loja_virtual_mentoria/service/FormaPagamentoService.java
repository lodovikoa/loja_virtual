package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.FormaPagamento;
import br.com.lodoviko.loja_virtual_mentoria.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento salvar(FormaPagamento formaPagamento) throws ExceptionMentoriaJava {

        if(formaPagamento.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar o ID no cadastro de nova Forma de Pagamento.");
        }

        if(formaPagamento.getDescricao().isBlank()) {
            throw new ExceptionMentoriaJava("Faltou informar a Descrição da Forma de Pagamento.");
        }

        if(formaPagamento.getEmpresa() == null || formaPagamento.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Empresa não informada.");
        }

        return formaPagamentoRepository.save(formaPagamento);
    }

    public List<FormaPagamento> listarFormasPagamentoPorEmpresa(Long idEmpresa) {
        return formaPagamentoRepository.listarFormasPagamentoPorEmpresa(idEmpresa);
    }
}
