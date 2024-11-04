package br.com.engenharia.projeto.ProjetoFinal.dtos.cartao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Bandeira;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCartao(
		
		@NotNull
		Long idCliente,
		
		boolean principal,
		
		@NotNull
		String numeroCartao,
		
		@NotNull
		String nomeImpresso,
		
		@NotNull
		String codigo,
		
		@NotNull
		Bandeira bandeira,
		
		@NotNull
		LocalDate dataExpiracao
		
									)

									{
}
