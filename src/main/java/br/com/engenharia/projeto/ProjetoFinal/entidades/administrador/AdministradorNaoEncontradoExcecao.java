package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

public class AdministradorNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AdministradorNaoEncontradoExcecao(String message) {
		super(message);
	}
}
