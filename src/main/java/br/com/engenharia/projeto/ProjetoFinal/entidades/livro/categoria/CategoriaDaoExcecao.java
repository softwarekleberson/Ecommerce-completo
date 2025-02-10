package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria;

public class CategoriaDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CategoriaDaoExcecao(String message) {
		super(message);
	}
}
