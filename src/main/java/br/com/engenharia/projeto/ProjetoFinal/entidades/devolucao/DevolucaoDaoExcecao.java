package br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao;

public class DevolucaoDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public DevolucaoDaoExcecao(String message) {
		super(message);
	}
}
