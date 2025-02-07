package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidades.user.Roles;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAdministrador(
		
		@NotNull
		String nome,
		
		@NotNull
		String email,
		
		@NotNull
		String senha,
		
		@NotNull
		String confirmarSenha,
		
		Roles roles
		
										) {
}
