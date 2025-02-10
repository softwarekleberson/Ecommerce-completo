package br.com.engenharia.projeto.ProjetoFinal.services.livro;

public class ValidacaoLivroServiceExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoLivroServiceExpetion(String message) {
		super(message);
	}
}
