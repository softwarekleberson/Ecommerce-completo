package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

public class EstoqueServiceExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EstoqueServiceExcecao(String message) {
		super(message);
	}
}
