package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAdministrador(
		
		@NotNull
		String nome,
		
		@NotNull
		String email,
		
		@NotNull
		String senha,
		
		@NotNull
		String confirmarSenha
		
										) {
}
