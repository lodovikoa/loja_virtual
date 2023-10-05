package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaFiscalCompra;
import br.com.lodoviko.loja_virtual_mentoria.repository.NotaFiscalCompraRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.NotaItemProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NotaFiscalCompraService {

    private final NotaFiscalCompraRepository notaFiscalCompraRepository;
    private final NotaItemProdutoRepository notaItemProdutoRepository;


    // Cadastrar
        /* Validações:
        *  ID precisa ser nulo
        *  Verificar se já existe descrição cadastrada
        *  Obrigatório ter Pessoa associada
        *  Obrigatório ter empresa associada
        *  Obrigatório ter contPagar associada*/

    public NotaFiscalCompra cadastrar(NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava {

        if(notaFiscalCompra.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar ID no cadastro!");
        }

        if(notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() == null){
            throw new ExceptionMentoriaJava("Faltou informar a Pessoa Jurica origem da Nota Fiscal!");
        }

        if(notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        if(notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Conta a Pagar");
        }

        if(notaFiscalCompraRepository.existsByDescricaoObsAndIdNot(notaFiscalCompra.getDescricaoObs(), notaFiscalCompra.getId())) {
            throw new ExceptionMentoriaJava("Nota Fiscal de Comnpra já cadastrada com a descrição: " + notaFiscalCompra.getDescricaoObs());
        }

        return notaFiscalCompraRepository.save(notaFiscalCompra);
    }

    // Alterar
        /* Validações
        *  ID não pode ser nulo e precisa ser localizado no banco de dados
        *  Verificar se já existe descrição cadastrada
        *  Obrigatório ter Pessoa associada
        *  Obrigatório ter empresa associada
        *  Obrigatório ter contaPagar associada */
    public NotaFiscalCompra alterar(NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava {

        if(notaFiscalCompra.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID da Nota Fiscal de Compra!");
        }

        if(notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() == null){
            throw new ExceptionMentoriaJava("Faltou informar a Pessoa Jurica origem da Nota Fiscal!");
        }

        if(notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa!");
        }

        if(notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Conta a Pagar");
        }

        if(notaFiscalCompraRepository.existsByDescricaoObsAndIdNot(notaFiscalCompra.getDescricaoObs(), notaFiscalCompra.getId())) {
            throw new ExceptionMentoriaJava("Nota Fiscal de Comnpra já cadastrada com a descrição: " + notaFiscalCompra.getDescricaoObs());
        }

        return notaFiscalCompraRepository.save(notaFiscalCompra);
    }

    // Deletar por id
        /* Deletr os itensNotaFiscalCompra primeiro */
    public void excluir(Long id) throws  ExceptionMentoriaJava {

        if(!notaFiscalCompraRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID (" + id + ") não encontrado");
        }

        // Verificar se existe intens da Nota Fiscal de Comnpra e exclui-los
        if(notaItemProdutoRepository.findByExisteNotaFiscalCompra(id)) {
            notaItemProdutoRepository.excluirItensNotaFiscalCompra(id);
        }

       notaFiscalCompraRepository.deleteById(id);
    }

    public List<NotaFiscalCompra> listar() {
        return notaFiscalCompraRepository.findAll();
    }

    // Buscar por id
    public Optional<NotaFiscalCompra> buscarPorId(Long id) throws ExceptionMentoriaJava {
        if(id == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID");
        }
        return  notaFiscalCompraRepository.findById(id);
    }

    // Buscar por Descrição
    public List<NotaFiscalCompra> buscarPorDescricao(String descricao) throws ExceptionMentoriaJava {
        if(descricao.isBlank()) {
            throw new ExceptionMentoriaJava("Faltou informar a descrição!");
        }

        return  notaFiscalCompraRepository.findByDescricaoObsContaining(descricao.trim());
    }
}