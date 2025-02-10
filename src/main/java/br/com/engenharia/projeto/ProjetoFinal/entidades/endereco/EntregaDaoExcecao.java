package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

public class EntregaDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EntregaDaoExcecao(String message) {
		super(message);
	}
}
