package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.repository.ContaPagarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContaPagarService {

    private final ContaPagarRepository contaPagarRepository;

    public ContaPagar salvar(ContaPagar contaPagar) throws ExceptionMentoriaJava {
        if(contaPagar.getId() != null || contaPagar.getId() == 0) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro!");
        }

        if(contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a pessoa responsável pela Conta a pagar.");
        }

        if(contaPagar.getPessoaFornecedor() == null || contaPagar.getPessoaFornecedor().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o Fornecedor");
        }

        if(contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa.");
        }

        return contaPagarRepository.save(contaPagar);
    }

}
