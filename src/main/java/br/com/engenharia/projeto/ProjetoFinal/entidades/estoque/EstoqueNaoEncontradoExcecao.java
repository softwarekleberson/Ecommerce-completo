package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

public class EstoqueNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EstoqueNaoEncontradoExcecao(String message) {
		super(message);
	}
}
