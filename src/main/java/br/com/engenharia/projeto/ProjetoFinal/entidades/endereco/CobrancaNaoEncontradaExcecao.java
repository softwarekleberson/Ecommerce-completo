package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

public class CobrancaNaoEncontradaExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CobrancaNaoEncontradaExcecao(String message) {
		super(message);
	}
}
