package br.com.engenharia.projeto.ProjetoFinal.services.estoque;

public class ValidarEstoqueServiceExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarEstoqueServiceExpetion(String message) {
		super(message);
	}
}
