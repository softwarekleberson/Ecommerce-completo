package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;

public record DadosDetalhamentoAdministrador(
		
		Long id,
		String nome,
		Email email
		
		) {

	public DadosDetalhamentoAdministrador(Administrador adminstrador) {
		this(adminstrador.getId(), adminstrador.getNome(), adminstrador.getEmail());
	}
}
