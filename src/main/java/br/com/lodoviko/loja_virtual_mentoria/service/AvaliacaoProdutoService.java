package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.AvaliacaoProduto;
import br.com.lodoviko.loja_virtual_mentoria.model.PessoaFisica;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import br.com.lodoviko.loja_virtual_mentoria.repository.AvaliacaoProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AvaliacaoProdutoService {

    private final AvaliacaoProdutoRepository avaliacaoProdutoRepository;

    /*
    * public AvaliacaoProduto salvar(AvaliacaoProduto avaliacaoProduto) {
    *   Verificar se a empresa foi informada
    *   Verificar se o produto foi informado
    *   Verificar se a Pessoa foi informada
    * }
    *
    * public void Excluir(Long id) {
    * }
    *
    * public List<AvaliacaoProduto> buscarAvaliacoesPorProduto(Long idProduto)
    *
    * public List<AvaliacaoProduto> buscarAvaliacoesPorProdutoEPessoa(Long idProduto ,Long idPessoa)
    *
    * public List<AvaliacaoProduto> busarAvaliacoesPorPessoa(Long idPessoa)
    * */

    public AvaliacaoProduto salvar(AvaliacaoProduto avaliacaoProduto) throws ExceptionMentoriaJava {
        if(avaliacaoProduto.getEmpresa() == null || avaliacaoProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Está faltando informar a Empresa.");
        }

        if(avaliacaoProduto.getProduto() == null || avaliacaoProduto.getProduto().getId() == null) {
            throw new ExceptionMentoriaJava("Está faltando informar o Produto.");
        }

        if(avaliacaoProduto.getPessoa() == null || avaliacaoProduto.getPessoa().getId() == null) {
            throw new ExceptionMentoriaJava("Está faltando informar a Pessoa que comprou o produto");
        }

        return avaliacaoProdutoRepository.save(avaliacaoProduto);
    }

    public void excluir(Long id) throws ExceptionMentoriaJava {
        if(!avaliacaoProdutoRepository.existsById(id)) {
            throw new ExceptionMentoriaJava("Não foi encontrado Avaliação com código " + id);
        }
        avaliacaoProdutoRepository.deleteById(id);
    }

    public List<AvaliacaoProduto> buscarAvaliacaoPorProduto(Long idProduto) throws ExceptionMentoriaJava {
        if(idProduto == null) {
            throw new ExceptionMentoriaJava("Faltou informar o código do Produto.");
        }
        Produto produto = new Produto(idProduto);
        return avaliacaoProdutoRepository.findByProduto(produto);
    }

    public List<AvaliacaoProduto> buscarAvaliacaoPorPessoa(Long idPessoa) throws ExceptionMentoriaJava {
        if(idPessoa == null) {
            throw new ExceptionMentoriaJava("Faltou informar o código da Pessoa");
        }
        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setId(idPessoa);

        return avaliacaoProdutoRepository.findByPessoa(pessoa);
    }

    public List<AvaliacaoProduto> buscarAvalidacaoProdutoPessoa(Long idProduto, Long idPessoa) {
        Produto produto = new Produto(idProduto);
        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setId(idPessoa);

        return avaliacaoProdutoRepository.findByProdutoAndPessoa(produto, pessoa);
    }

}
