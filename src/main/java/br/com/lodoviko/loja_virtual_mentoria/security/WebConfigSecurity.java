package br.com.lodoviko.loja_virtual_mentoria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                         /* Permissão para qualquer pessoa ter acesso ao login */
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                         /* Permissões para Registros de Acesso */
                        .requestMatchers(HttpMethod.POST, "/acesso").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/acesso/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/acesso/descricao/*").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/acesso/*").hasRole("ADMIN")

                        /* Permissões para Registros de Pessoa */
                        .requestMatchers(HttpMethod.POST, "/pessoa/pj").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pessoa/pj/**").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/pessoa/pf").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "pessoa/pf/**").hasRole("USER")

                        /* Permissões para Categoria do Produto */
                        .requestMatchers(HttpMethod.POST, "/categoriaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/categoriaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/categoriaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/categoriaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/categoriaProduto/*").hasRole("USER")

                        /* Permissões para Marca do Produto */
                        .requestMatchers(HttpMethod.POST, "/marcaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/marcaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/marcaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/marcaProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/marcaProduto/*").hasRole("USER")

                        /* Permissões para Produto */
                        .requestMatchers(HttpMethod.POST, "/produto").hasRole("USER")

                        /* Permissões para Conta Pagar */
                        .requestMatchers(HttpMethod.POST, "/contaPagar").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/contaPagar").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/contaPagar").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/contaPagar/excluir/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/contaPagar/descricao/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/contaPagar/id/*").hasRole("USER")

                        /* Permissões para Nota Fiscal de Compra - notaFiscalCompra */
                        .requestMatchers(HttpMethod.POST, "/notaFiscalCompra").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/notaFiscalCompra").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/notaFiscalCompra").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/notaFiscalCompra/excluir/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/notaFiscalCompra/descricao/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/notaFiscalCompra/id/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "notaFiscalCompra/relatorioProdutoNFCompra/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"notaFiscalCompra/relatorioProdutoAlertaEstoque/**").hasRole("USER")

                        /* Permissões para Nota Item Produto - notaItemProduto */
                        .requestMatchers(HttpMethod.POST, "/notaItemProduto").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/notaItemProduto").hasRole("USER")

                        /* Permissão para consultar CEPs, CNPJs */
                        .requestMatchers(HttpMethod.GET, "/pessoa/consultarCep/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pessoa/consultarCnpj/*").hasRole("USER")

                        /* Permissão para Imagem do Produto */
                        .requestMatchers(HttpMethod.GET, "imagemProduto/listarPorProduto/*").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "imagemProduto/excluir/*").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "imagemProduto/excluirPorProduto/*").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "imagemProduto/cadastrar").hasRole("USER")

                        /* Permissão para Avaliação do Produto */
                        .requestMatchers(HttpMethod.POST, "avaliacaoProduto/cadastrar").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "avaliacaoProduto/listarPorProduto/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "avaliacaoProduto/listarPorPessoa/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "avaliacaoProduto/listarPorProdutoPessoa/*/*").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "avaliacaoProduto/excluir/*").hasRole("USER")

                        /* Permissão para Forma de Pagamento */
                        .requestMatchers(HttpMethod.POST, "formaPagamento/cadastrar").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "formaPagamento/listar/idEmpresa/*").hasRole("USER")

                        /* Permissão para Vendas da Loja Virtual */
                        .requestMatchers(HttpMethod.POST, "vendaLoja/cadastrar").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "vendaLoja/id/*").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "vendaLoja/excluir/*").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "vendaLoja/excluirLogicamente/*").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "vendaLoja/reativarLogicamente/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "vendaLoja/listarVendaDinamica/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "vendaLoja/relatorioStatusVenda/**").hasRole("USER")

                        /* Permissões para Status Rastreio */
                        .requestMatchers(HttpMethod.GET,"statusRastreio/listar/**").hasRole("USER")

                        /* Permissões para Nota Fiscal de Venda */
                        .requestMatchers(HttpMethod.GET, "notaFiscalVenda/idVenda/*").hasRole("USER")

                        /* Permissões para Cupom de Desconto  */
                        .requestMatchers(HttpMethod.POST, "cupomDesconto/cadastrar").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "cupomDesconto/alterar").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "cupomDesconto/excluir").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "cupomDesconto/buscarPorId/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "cupomDesconto/listar/idEmpresa/*").hasRole("USER")

                         /* Nega todas as demais solicitações */
                        .anyRequest().denyAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
