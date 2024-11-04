package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.EstadoDoProdutoAoEntrarNoEstoque;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDevolucao(
		
		@NotNull
		String codigoPedido,
		
		@NotNull
		String codigoDevolucao,
		
		@NotNull
		EsperandoDevolucaoOuRecebido esperandoDevolucaoOuRecebido,
		
		@NotNull
		ProdutoVoltaParaEstoque produtoVoltaParaEstoque,
		
		@NotNull
		EstadoDoProdutoAoEntrarNoEstoque estadoProduto
		
		) {
}
