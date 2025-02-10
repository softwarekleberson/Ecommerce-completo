package br.com.engenharia.projeto.ProjetoFinal.entidades.cupom;

public class CupomDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CupomDaoExcecao(String message) {
		super(message);
	}
}
