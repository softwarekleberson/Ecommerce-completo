package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

public class AdministradorDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AdministradorDaoExcecao(String message) {
		super(message);
	}
}
