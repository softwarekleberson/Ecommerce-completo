package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

public interface RepositorioDeAdministrador {
	
	public void salvar(Administrador adminstrador);
	public void deletar(Long id);
	boolean verificaEmailCadastrado(String email);
	Administrador pegaAdministradorAleatorio();
}
