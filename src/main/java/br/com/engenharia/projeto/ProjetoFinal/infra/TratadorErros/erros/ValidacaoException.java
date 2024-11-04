package br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
