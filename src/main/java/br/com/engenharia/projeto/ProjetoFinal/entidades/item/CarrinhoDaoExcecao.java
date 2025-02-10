package br.com.engenharia.projeto.ProjetoFinal.entidades.item;

public class CarrinhoDaoExcecao extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public CarrinhoDaoExcecao(String message) {
		super(message);
	}
}
