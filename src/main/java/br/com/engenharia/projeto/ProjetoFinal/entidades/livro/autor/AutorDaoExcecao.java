package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor;

public class AutorDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AutorDaoExcecao(String message) {
		super(message);
	}
}
