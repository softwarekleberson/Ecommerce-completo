package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPagamento(
		
		@NotNull
		Long idEntrega,
		
		@NotNull
		Long idCobranca,
		
		Long idCartao1,
		Long idCartao2,
		String cupom1,
		String cupom2
		
									) {
}