package br.com.engenharia.projeto.ProjetoFinal.entidades.cupom;

public class CupomNaoEcontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CupomNaoEcontradoExcecao(String message) {
		super(message);
	}
}
