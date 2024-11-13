package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

public record DadosCadastroPagamento(
				
		Long idCartao1,
		Long idCartao2,
		String cupom1,
		String cupom2,
		boolean salvarCartao
									) {
}