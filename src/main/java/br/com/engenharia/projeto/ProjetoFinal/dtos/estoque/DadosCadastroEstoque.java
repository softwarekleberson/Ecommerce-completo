package br.com.engenharia.projeto.ProjetoFinal.dtos.estoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.EstadoDoProdutoAoEntrarNoEstoque;

public record DadosCadastroEstoque(
		
		Long idLivro,
		int quantidade,
		BigDecimal valorCusto,
		LocalDate dataEntrada,
		String fornecedor,
		EstadoDoProdutoAoEntrarNoEstoque estadoProduto
		
		) {
}
