package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.NotaItemProduto;
import br.com.lodoviko.loja_virtual_mentoria.repository.NotaFiscalCompraRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.NotaItemProdutoRepository;
import br.com.lodoviko.loja_virtual_mentoria.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotaItemProdutoService {

    private final NotaItemProdutoRepository notaItemProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final NotaFiscalCompraRepository notaFiscalCompraRepository;

    /******** Para o Repository ********/
    /* Buscar NotaItem por Produto.id e NotaFiscalCompra.id */
    /* Buscar NotaItem por Produto.id */
    /* Buscar NotaItem por NotaFiscal.id */
    /* Buscar NotaItem por Empresa.id */

    /********* Para o Controller *************/
    /* public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto nip){} */


    /* Excluir a foreykey da tabela de tb_produto - nota_item_produto_id */
    /*********** Para o Service ***********************/
    /* SalvarItemProduto
    *        Verificar se o produto informado existe (Produto deve ser informadol)
    *        A NotaFiscalCompra deve ser informada
    *        A Empresa deve ser informada
    *        Verificar se já existe produto cadastrado para a nota (notaItemProduto.getProduto.getId(), notaItemProduto.getNotaFiscalCompra.getId())
    *        Salvar a NotaItemProduto */

    public NotaItemProduto salvar(NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava {
        if(notaItemProduto.getId() != null) {
            throw new ExceptionMentoriaJava("Não informar o ID na inclusão de novo produto na NF");
        }

        if(notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o Produto");
        }

        if(!produtoRepository.existsById(notaItemProduto.getProduto().getId())) {
            throw new ExceptionMentoriaJava("O produto informado não foi encontrado.");
        }

        if(notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a NF de Compra");
        }

        if(!notaFiscalCompraRepository.existsById(notaItemProduto.getNotaFiscalCompra().getId())) {
            throw new ExceptionMentoriaJava("A NF compra informada não foi encontrada!");
        }

        if(notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa");
        }

        if(notaItemProdutoRepository.existsByProdutoAndNotaFiscalCompra(notaItemProduto.getProduto(), notaItemProduto.getNotaFiscalCompra())) {
            throw new ExceptionMentoriaJava("O produto informado já encontra-se cadastrado para a NF informada.");
        }

        return notaItemProdutoRepository.save(notaItemProduto);
    }

    public NotaItemProduto alterar(NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava {
        if(notaItemProduto.getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o ID da Nota Item Produto");
        }

        if(notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar o Produto");
        }

        if(!produtoRepository.existsById(notaItemProduto.getProduto().getId())) {
            throw new ExceptionMentoriaJava("O produto informado não foi encontrado.");
        }

        if(notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a NF de Compra");
        }

        if(!notaFiscalCompraRepository.existsById(notaItemProduto.getNotaFiscalCompra().getId())) {
            throw new ExceptionMentoriaJava("A NF compra informada não foi encontrada!");
        }

        if(notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa");
        }

        if(notaItemProdutoRepository.existsByProdutoAndNotaFiscalCompraAndIdNot(notaItemProduto.getProduto(), notaItemProduto.getNotaFiscalCompra(), notaItemProduto.getId())) {
            throw new ExceptionMentoriaJava("O produto informado já encontra-se cadastrado para a NF informada.");
        }

        return notaItemProdutoRepository.save(notaItemProduto);
    }
}
