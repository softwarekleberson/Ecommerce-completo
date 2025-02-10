package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao;

public class CartaoDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CartaoDaoExcecao(String message) {
		super(message);
	}
}
