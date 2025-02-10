package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro;

public class LivroDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public LivroDaoExcecao(String message) {
		super(message);
	}
}
