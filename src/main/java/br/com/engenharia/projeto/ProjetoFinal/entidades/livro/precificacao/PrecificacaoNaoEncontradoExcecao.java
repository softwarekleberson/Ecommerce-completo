package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao;

public class PrecificacaoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PrecificacaoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
