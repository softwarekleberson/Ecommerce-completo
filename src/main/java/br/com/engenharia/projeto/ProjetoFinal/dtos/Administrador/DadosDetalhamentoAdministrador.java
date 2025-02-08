package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;

public record DadosDetalhamentoAdministrador(
		
		Long id,
		String nome,
		String email
		
		) {

	public DadosDetalhamentoAdministrador(Administrador adminstrador) {
		this(adminstrador.getId(), adminstrador.getNome(), adminstrador.getEmail());
	}
}
