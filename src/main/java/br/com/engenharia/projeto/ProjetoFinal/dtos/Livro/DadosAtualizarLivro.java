package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarLivro(
		
		@NotNull
		Long id,
		
		LocalDate data,
		BigDecimal preco,
		boolean ativo,
		String titulo,
		String isbn,
		int paginas,
		String sinopse,
		String codigoBarra,
		
		DadosAtualizarDimensao dimensoes,

		String editora,
		BigDecimal precificacao,
		String edicao,
		
		List<DadosAtualizarImagem> imagem,
		List<DadosAtulizacaoAutor> autor,
		List<DadosAtualizarCategoria> categoria
		
											) {

}
