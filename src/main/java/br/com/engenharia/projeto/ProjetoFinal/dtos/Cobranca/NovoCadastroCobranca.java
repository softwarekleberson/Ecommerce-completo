package br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca;

import jakarta.validation.constraints.NotNull;

public record NovoCadastroCobranca(
		
		@NotNull
		Long idCliente,
		
		@NotNull
		String tipoResidenciaCobranca,
		
		@NotNull
		String tipoLogradouroCobranca,
		
		@NotNull
		String logradouroCobranca,
		
		@NotNull
		String numeroCobranca,
		
		@NotNull
		String bairroCobranca,
		
		@NotNull
		String cepCobranca,
		
		String observacaoCobranca,

		@NotNull
		String cidadeCobranca,
		
		@NotNull
		String estadoCobranca,
		
		@NotNull
		String paisCobranca
		
									) {
}
