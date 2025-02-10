package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

public class LivroDaoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroDaoExcecao(String message) {
		super(message);
	}
}
