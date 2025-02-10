package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

public class ValidacaoClienteServiceExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidacaoClienteServiceExpetion(String message) {
		super(message);
	}

}
