package br.com.engenharia.projeto.ProjetoFinal.dtos.estoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;

public record DadosDetalhamentoEstoque(
		
		Long id,
		int quantidade,
		BigDecimal valorCusto,
		LocalDate dataEntrada
		
		) {
	
	public DadosDetalhamentoEstoque(Estoque dados) {
		this(dados.getId(), dados.getQuantidade(), dados.getValorCusto(),
			 dados.getDataEntrada());
	}

}