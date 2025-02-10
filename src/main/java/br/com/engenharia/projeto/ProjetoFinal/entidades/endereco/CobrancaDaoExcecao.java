package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

public class CobrancaDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CobrancaDaoExcecao(String message) {
		super(message);
	}
}
