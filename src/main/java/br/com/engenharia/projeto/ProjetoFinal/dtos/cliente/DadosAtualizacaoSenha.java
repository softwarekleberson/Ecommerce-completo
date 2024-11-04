package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSenha(
		
		@NotNull
		Long idCliente,
		
		@NotNull
		String senha,
		
		@NotNull
		String confirmarSenha
		
									) {
}