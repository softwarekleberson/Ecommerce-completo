package br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega;

import jakarta.validation.constraints.NotNull;

public record NovoCadastroEntrega(
		
		@NotNull
		Long idCliente,
		
		@NotNull
		String tipoResidenciaEntrega,
		
		@NotNull
		String tipoLogradouroEntrega,
		
		@NotNull
		String logradouroEntrega,
		
		@NotNull
		String numeroEntrega,
		
		@NotNull
		String bairroEntrega,
		
		@NotNull
		String cepEntrega,
		
		String observacaoEntrega,

		@NotNull
		String cidadeEntrega,
		
		@NotNull
		String estadoEntrega,
		
		@NotNull
		String paisEntrega,
		
		@NotNull
		String fraseEntregaEntrega
		
								  ) 
								  {

}
