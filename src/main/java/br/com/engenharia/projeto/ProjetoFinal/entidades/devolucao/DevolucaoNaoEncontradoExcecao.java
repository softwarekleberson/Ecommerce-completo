package br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao;

public class DevolucaoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public DevolucaoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
