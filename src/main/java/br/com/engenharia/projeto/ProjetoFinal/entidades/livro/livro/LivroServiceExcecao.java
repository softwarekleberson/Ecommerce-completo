package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro;

public class LivroServiceExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public LivroServiceExcecao(String message) {
		super(message);
	}
}
