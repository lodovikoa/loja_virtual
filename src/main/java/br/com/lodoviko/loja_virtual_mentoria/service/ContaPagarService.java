package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.ContaPagar;
import br.com.lodoviko.loja_virtual_mentoria.repository.ContaPagarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContaPagarService {

    private final ContaPagarRepository contaPagarRepository;

    public ContaPagar salvar(ContaPagar contaPagar) throws ExceptionMentoriaJava {
        if(contaPagar.getId() != null ) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro!");
        }


        if(contaPagarRepository.existsByDescricao(contaPagar.getDescricao())) {
            throw new ExceptionMentoriaJava("Conta pagar já cadastrada com a descrição: " + contaPagar.getDescricao());
        }

        if(contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a pessoa responsável pela Conta a pagar!");
        }

        if(contaPagar.getPessoaFornecedor() == null || contaPagar.getPessoaFornecedor().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o Fornecedor!");
        }

        if(contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        return contaPagarRepository.save(contaPagar);
    }

    public ContaPagar alterar(ContaPagar contaPagar) throws ExceptionMentoriaJava {
        if(contaPagar.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID da conta pagar");
        }

        if(!contaPagarRepository.existsById(contaPagar.getId())) {
            throw new ExceptionMentoriaJava("Conta pagar não localizada!");
        }

        if(contaPagarRepository.existsByDescricaoAndIdNot(contaPagar.getDescricao(), contaPagar.getId())) {
            throw new ExceptionMentoriaJava("Já existe conta pagar com a descrição: " + contaPagar.getDescricao());
        }

        if(contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a pessoa responsável pela Conta a pagar!");
        }

        if(contaPagar.getPessoaFornecedor() == null || contaPagar.getPessoaFornecedor().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o Fornecedor!");
        }

        if(contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        return contaPagarRepository.save(contaPagar);
    }

    public List<ContaPagar> listar() {
        return contaPagarRepository.findAll();
    }

    public List<ContaPagar> buscarPorDescricao(String descricao) throws ExceptionMentoriaJava {
        if(descricao.isBlank()) {
            throw new ExceptionMentoriaJava("Faltou informar a descrição!");
        }

        return contaPagarRepository.findByDescricaoContaining(descricao.trim());
    }

    public Optional<ContaPagar> buscarPorId(Long id) throws ExceptionMentoriaJava {
        if(id == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID");
        }

        return  contaPagarRepository.findById(id);
    }

    public void excluir(Long id) throws ExceptionMentoriaJava {
        if(!contaPagarRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID (" + id + ") Não encontrado!");
        }

        contaPagarRepository.deleteById(id);
    }

}
