package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSenha(
		
		@NotNull
		String senha,
		
		@NotNull
		String confirmarSenha
		
									) {
}