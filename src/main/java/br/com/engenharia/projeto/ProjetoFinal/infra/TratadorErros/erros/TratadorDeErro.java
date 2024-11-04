package br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class TratadorDeErro {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
	   return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {	        var erros = ex.getFieldErrors();
	   return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
	   return ResponseEntity.badRequest().body(ex.getMessage());
    }

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity tratarErroRegraDeNegocio(ValidationException ex) {
	   return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity tratarErro500(Exception ex) {
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
	 }

	private record DadosErroValidacao(String nome, String mensagem){
	    public DadosErroValidacao(FieldError erro) {
	      this(erro.getField(), erro.getDefaultMessage());
	    }
	}
}