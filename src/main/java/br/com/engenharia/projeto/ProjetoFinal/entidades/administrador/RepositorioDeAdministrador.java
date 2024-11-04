package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;

public interface RepositorioDeAdministrador {
	
	public void salvar(Administrador adminstrador);
	public void deletar(Long id);
	boolean verificaEmailCadastrado(Email email);
	Administrador pegaAdministradorAleatorio();
}
