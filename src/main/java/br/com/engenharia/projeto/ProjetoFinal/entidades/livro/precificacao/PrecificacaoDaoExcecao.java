package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao;

public class PrecificacaoDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PrecificacaoDaoExcecao(String message) {
		super(message);
	}
}
