package br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento;

public class PagamentoDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PagamentoDaoExcecao(String message) {
		super(message);
	}
}
