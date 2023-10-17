package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Endereco;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.VendaCompraLojaVirtual;
import br.com.lodoviko.loja_virtual_mentoria.repository.EnderecoRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.PessoaFisicaRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.VendaCompraLojaVirtualRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VendaCompraLojaVirtualService {

    private final VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    /*

    * public VendaCompraLojaVirtual salvar(VendaCompraLojaVirtual vd) {
    *   Validar associação com Pessoa
    *   Validar associação com Produto
    * }
    *  */

    public VendaCompraLojaVirtual salvar(VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava {
        if(vendaCompraLojaVirtual.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar o ID no cadastro de Venda de Produto.");
        }

        if(vendaCompraLojaVirtual.getPessoa() == null || vendaCompraLojaVirtual.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Pessoa que fez a compra.");
        }

        // Buscar Endereço cadastrado
        Endereco enderecoEntrega = enderecoRepository.findById(vendaCompraLojaVirtual.getEnderecoEntrega().getId()).get();
        Endereco enderecoCobranca = enderecoRepository.findById(vendaCompraLojaVirtual.getEnderecoCobranca().getId()).get();

        // Buscar Pessoa que fez a compra cadastrada
        PessoaFisica pessoa = pessoaFisicaRepository.findById(vendaCompraLojaVirtual.getPessoa().getId()).get();

        vendaCompraLojaVirtual.setEnderecoEntrega(enderecoEntrega);
        vendaCompraLojaVirtual.setEnderecoCobranca(enderecoCobranca);

        vendaCompraLojaVirtual.setPessoa(pessoa);

        return vendaCompraLojaVirtualRepository.save(vendaCompraLojaVirtual);
    }
}
