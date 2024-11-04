package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoEstoque(
		
		int quantidade,
		BigDecimal valorCusto,
		LocalDate dataEntrada,
		String fornecedor
									) {
}
