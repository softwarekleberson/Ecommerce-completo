package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

public class EstoqueDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EstoqueDaoExcecao(String message) {
		super(message);
	}
}
