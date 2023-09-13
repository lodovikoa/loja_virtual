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

                        /* Permissão para consultar CEPs, CNPJs */
                        .requestMatchers(HttpMethod.GET, "/pessoa/consultarCep/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pessoa/consultarCnpj/*").hasRole("USER")

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
