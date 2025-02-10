package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

public class ValidacaoEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoEmailException(String message) {
		super(message);
	}
}
