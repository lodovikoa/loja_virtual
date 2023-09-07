package br.com.lodoviko.loja_virtual_mentoria.exception;

import br.com.lodoviko.loja_virtual_mentoria.model.dto.ObjetoErroDTO;
import br.com.lodoviko.loja_virtual_mentoria.service.EmailSendService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

    @Autowired
    EmailSendService emailSendService;

    @ExceptionHandler(ExceptionMentoriaJava.class)
    public ResponseEntity<Object> handleExceptionCustom(ExceptionMentoriaJava ex) {
        ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO(ex.getMessage(), HttpStatus.OK.toString());

        return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);
    }

    /* Captura exceções do projeto */
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        var msg = "";
        if(ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

            for (ObjectError objectError : list) {
                msg += objectError.getDefaultMessage() + "\n";
            }
        } else if ( ex instanceof HttpMessageNotReadableException) {
            msg = "Não está sendo enviado dados para o BODY corpo da requisição";
        } else {
            msg = ex.getMessage();
        }

        ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO(msg, statusCode.value() + " ==> " + statusCode.toString());

        /* Joga o erro para o console */
        ex.printStackTrace();

        this.enviarEmailEquipeResponsavel(ex);

        return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
    protected ResponseEntity<Object> handleExceprionDataIntegry(Exception ex) {
        var msg = "";

        if(ex instanceof DataIntegrityViolationException) {
            msg = "Erro de integridade no Banco: " + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
        } else if(ex instanceof ConstraintViolationException) {
            msg = "Erro de chave estrangeira: " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
        } else if(ex instanceof SQLException) {
            msg = "Erro de SQL do Banco: " + ((SQLException) ex).getCause().getCause().getMessage();
        } else {
            msg = ex.getMessage();
        }

        ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO(msg, HttpStatus.INTERNAL_SERVER_ERROR.toString());

        /* Joga o erro para o console */
        ex.printStackTrace();

        this.enviarEmailEquipeResponsavel(ex);

        return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void enviarEmailEquipeResponsavel(Exception ex) {
        try {
            String dsEmail = "lodoviko@hotmail.com";
            emailSendService.enviarEmailHtml("Erro na loja virtual", ExceptionUtils.getStackTrace(ex),dsEmail);
            System.out.println("E-mail com trace de erro enviado para: " + dsEmail);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
