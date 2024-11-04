package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor;

public class AutorNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AutorNaoEncontradoExcecao(String message) {
		super(message);
	}
}
