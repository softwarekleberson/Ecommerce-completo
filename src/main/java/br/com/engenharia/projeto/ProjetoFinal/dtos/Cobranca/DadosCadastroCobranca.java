package br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCobranca(
		
		Long idCliente,
		
		boolean principal,
		
		@NotNull
		String receptorCobranca,
		
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
