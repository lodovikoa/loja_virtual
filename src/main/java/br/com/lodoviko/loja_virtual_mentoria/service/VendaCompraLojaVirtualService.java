package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.StatusRastreio;
import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;
import br.com.lodoviko.loja_virtual_mentoria.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VendaCompraLojaVirtualService {

    private final VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final StatusRastreioRepository statusRastreioRepository;
    private final NotaFiscalVendaRepository notaFiscalVendaRepository;

    public VendaCompraLojaVirtual salvar(VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava {
        if(vendaCompraLojaVirtual.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar o ID no cadastro de Venda de Produto.");
        }

        if(vendaCompraLojaVirtual.getPessoa() == null || vendaCompraLojaVirtual.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Pessoa que fez a compra.");
        }

        if(vendaCompraLojaVirtual.getItensVendaLoja() == null || vendaCompraLojaVirtual.getItensVendaLoja().size() == 0) {
            throw new ExceptionMentoriaJava("Faltou informar os produtos da venda.");
        }

        // Buscar Endereço cadastrado
        Endereco enderecoEntrega = enderecoRepository.findById(vendaCompraLojaVirtual.getEnderecoEntrega().getId()).get();
        Endereco enderecoCobranca = enderecoRepository.findById(vendaCompraLojaVirtual.getEnderecoCobranca().getId()).get();

        // Buscar Pessoa que fez a compra cadastrada
        PessoaFisica pessoa = pessoaFisicaRepository.findById(vendaCompraLojaVirtual.getPessoa().getId()).get();

        vendaCompraLojaVirtual.setEnderecoEntrega(enderecoEntrega);
        vendaCompraLojaVirtual.setEnderecoCobranca(enderecoCobranca);

        vendaCompraLojaVirtual.setPessoa(pessoa);

        // Ajustar os produtos informados na venda
        for(int i = 0; i < vendaCompraLojaVirtual.getItensVendaLoja().size(); i++) {
            vendaCompraLojaVirtual.getItensVendaLoja().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
            vendaCompraLojaVirtual.getItensVendaLoja().get(i).setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
        }

        vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.save(vendaCompraLojaVirtual);

        StatusRastreio statusRastreio = new StatusRastreio(null,"Loja Local", "Local", "ES", "Venda Inicial", vendaCompraLojaVirtual, vendaCompraLojaVirtual.getEmpresa());
        statusRastreioRepository.save(statusRastreio);

        return vendaCompraLojaVirtual;
    }

    public VendaCompraLojaVirtual consultarPorId(Long id) throws ExceptionMentoriaJava {
       // return vendaCompraLojaVirtualRepository.findById(id);
        return vendaCompraLojaVirtualRepository.findByIdAndExcluidoIsFalse(id);
    }

    public  void excluirVendaTotal(Long idVenda) throws ExceptionMentoriaJava {
        if(!vendaCompraLojaVirtualRepository.existsById(idVenda)) {
            throw new ExceptionMentoriaJava("Registro com ID " + idVenda + " não encontrado.");
        }

        // Alterar todas as vendas registradas na Nota Fiscal de Venda setando NULL no identificador da Venda na Nota Fiscal de Venda
        notaFiscalVendaRepository.alterarTodasVendasParaPermitirExclusao(idVenda);

        // Excluir todas as vendas registradas na Nota Fiscal de Venda cujo associação com a Venda seja NULL, ou seja não tem associação
        notaFiscalVendaRepository.excluirTodasNotaFiscalSemVendaAssociada();

        // Excluir todos registros de rastreio
        statusRastreioRepository.excluirTodosRastreiosDeUmaVenda(idVenda);

        // Excluir a Venda
        vendaCompraLojaVirtualRepository.deleteById(idVenda);
    }

    public void excluirLogicamente(Long id) throws ExceptionMentoriaJava {
        if(!vendaCompraLojaVirtualRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID da Venda não localizado.");
        }
        if(vendaCompraLojaVirtualRepository.existsByIdAndExcluidoIsTrue(id)) {
            throw new ExceptionMentoriaJava("ID " + id + " Já encontra-se excluido logicamente.");
        }

        vendaCompraLojaVirtualRepository.excluirLogicamente(id);
    }

    public void reativarLogicamente(Long id) throws ExceptionMentoriaJava {
        if(!vendaCompraLojaVirtualRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("ID da Venda não localizado.");
        }

        if(vendaCompraLojaVirtualRepository.existsByIdAndExcluidoIsFalse(id)) {
            throw new ExceptionMentoriaJava("ID " + id + " Já encontra-se ativo.");
        }

        vendaCompraLojaVirtualRepository.reativarLogicamente(id);
    }

    /*
    * Listar todas as vendas que pussui um determinado produto.
    * Query("select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
    *  */
    public List<VendaCompraLojaVirtual> listarVendasPorProduto(Long idProduto) throws ExceptionMentoriaJava {
        return vendaCompraLojaVirtualRepository.listarVendasPorProduto(idProduto);
    }
}
