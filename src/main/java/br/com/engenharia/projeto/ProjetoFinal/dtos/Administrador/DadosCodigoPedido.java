package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import jakarta.validation.constraints.NotNull;

public record DadosCodigoPedido(
		
		@NotNull
		String codigo
		
						  ){
}
