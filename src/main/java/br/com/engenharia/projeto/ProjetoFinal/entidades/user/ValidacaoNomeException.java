package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

public class ValidacaoNomeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoNomeException(String message) {
		super(message);
	}
}
