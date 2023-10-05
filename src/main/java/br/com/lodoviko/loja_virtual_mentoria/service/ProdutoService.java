package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Produto;
import br.com.lodoviko.loja_virtual_mentoria.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) throws ExceptionMentoriaJava {

        if(produto.getId() != null ) {
            throw new ExceptionMentoriaJava("Não informar o ID do produto no cadastro.");
        }

        if(produto.getEmpresa() == null || produto.getEmpresa().getId() == null) {
            throw new ExceptionMentoriaJava("Faltou informar a Empresa.");
        }

        if(produtoRepository.existsByNomeAndEmpresa(produto.getNome().trim(), produto.getEmpresa())) {
            throw new ExceptionMentoriaJava("Já existe um produto com nome " + produto.getNome() + " cadastrado.");
        }

        if(produto.getTipoUnidade().isBlank()) {
            throw new ExceptionMentoriaJava("Faltou preencher o Tipo de Unidade");
        }

        if(produto.getNome().trim().length() < 10) {
            throw new ExceptionMentoriaJava("O nome do produto precisa ter 10 ou mais caracteres");
        }

        if(produto.getQtdEstoque() == null || produto.getQtdEstoque() < 1) {
            throw new ExceptionMentoriaJava("Faltou informar a quantidade para dar entrada no estoque do produto");
        }

        if(produto.getImagens().isEmpty() || produto.getImagens().size() < 3 || produto.getImagens().size() > 6) {
            throw new ExceptionMentoriaJava("Precisa informar de 3 a 6 imagens do produto que está sendo cadastrado.");
        }

        for(int x = 0; x < produto.getImagens().size(); x++) {
            produto.getImagens().get(x).setProduto(produto);
            produto.getImagens().get(x).setEmpresa(produto.getEmpresa());

            produto.getImagens().get(x).setImagemMiniatura("miniimagem");
        }



        // Mais validações
        /*
        -- OK -- Validar Tipo de Unidade (tipoUnidade), que deve ser informada
        -- OK -- Validar nome do produto (nome) que deve ter 10 ou mais caracteres
        -- OK -- Validar quantidade de estoque que deve ser informado pelo menos 1 ou mais

        Validar AlertaEstoque && produto.getqtdeEstoque <= 1 enviar email
            StringBuider html = new Stringbuilder();
            html.append("<h2>")
                .append("Produto: " + produto.getNome())
                .append(" com estoque baixo: " + produto.getQtdEstoque());
                .append("<p> ID Prod.: " + produto.getId())
                .append("</p>");
             if(produto.getEmpresa().getEmail() != null) {
                serviceSendEmail.enviarEmailHtml("Produto com estoque baixo", html.toString(), produto.getEmpresa.getEmail());
             }

          --- Mapear na classe Produto.java a relação de Produto com ImagemProduto
          -- OK --     @OneToMany(mappedBy = "produto", orphanRemloval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
          -- OK --    private List<ImagemProduto> imagens = new ArrayList<ImagemProduto>();

          -- OK -- Validar Imagen informada no cadastro do produto (Imanges não pode ser null, vazio ou tamanho = 0)
          -- OK -- Validar Imagen deve ter pelo menos 3 imagens e não pode ser maior que 6

            Associar as imagens ao produto que está sendo cadastrado
            Trabalhar com Imagem base64
            if(produto.getId() == null) {
                for(int x = 0; x < produto.getImagens().size(); x++) {
                   -- OK -- produto.getImagens().get(x).setProduto(produto);
                   -- OK -- produto.getImagens().get(x).setEmpresa(produto.getEmpresa());

                    String base64Imagem = "";
                    if(produto.getImagens().get(x).getImagemOriginal().contains("data:image")) {
                        base64Imagem = produto.getImagens().get(x).getImagemOriginal.split(",")[1];
                    } else {
                        base64Imagem = produto.getImagens().get(x).getImagemOriginal();
                    }

                    byte[] imagemBytes = DatatypeConverter.parseBase64Binary(base64Imagem);
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                    if(bufferedImage != null) {
                        int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
                        int largura = Integer.parseInt("800");
                        int altura = Integer.parseInt("600");

                        BufferedImage resizeImage = new BufferedImage(largura, altura, type);
                        Graphics2D g = resizeImage.createGraphics();
                        g.drawImage(bufferedImage, 0, 0, largura, altura, null);
                        g.dispose();

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(resizedImage, "png", baos);

                        String miniImgBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());

                        produto.getImagens().get(x).setImagemMiniatura(miniImgBase64);

                        bufferedImage.flush();
                        resizedImage.flush();
                        baos.flush();
                        baos.close();
                    }
                }
            }

            Parou video em 53:10
        * */

        return produtoRepository.save(produto);
    }
}
