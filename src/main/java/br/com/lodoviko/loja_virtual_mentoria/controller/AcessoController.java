package br.com.lodoviko.loja_virtual_mentoria.controller;

import br.com.lodoviko.loja_virtual_mentoria.exception.ExceptionMentoriaJava;
import br.com.lodoviko.loja_virtual_mentoria.model.Acesso;
import br.com.lodoviko.loja_virtual_mentoria.service.AcessoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RestController
@RequestMapping("acesso")
public class AcessoController {

    private AcessoService acessoService;

    @PostMapping
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava {


        String encryptPassowrd = new BCryptPasswordEncoder().encode("12345678");
        System.out.println(encryptPassowrd);

        Acesso acessoSalvo = acessoService.save(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAcesso(@PathVariable("id") Long id) {
        acessoService.excluir(id);
        return new  ResponseEntity("Acesso removido", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acesso> consultarAcessoPorID(@PathVariable("id") Long id) throws ExceptionMentoriaJava {
        Acesso acesso =  acessoService.consultarPorID(id);
        return new ResponseEntity(acesso, HttpStatus.OK);
    }

    @GetMapping(value = "/descricao/{desc}")
    public ResponseEntity<List<Acesso>> consultarAcessoPorDesc(@PathVariable("desc") String desc) {

        List<Acesso> acessos = acessoService.buscarPorDesc(desc);
        return new ResponseEntity<List<Acesso>>(acessos, HttpStatus.OK);
    }
}
