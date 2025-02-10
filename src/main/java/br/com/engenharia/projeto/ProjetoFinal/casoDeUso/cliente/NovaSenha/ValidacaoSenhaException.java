package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

public class ValidacaoSenhaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoSenhaException(String message) {
		super(message);
	}
}
