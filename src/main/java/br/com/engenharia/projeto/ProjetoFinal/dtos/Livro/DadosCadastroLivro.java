package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLivro(
		
		@NotNull
		Long idPrecificacao,
		
		@NotNull
		LocalDate data,
		
		@NotNull
		BigDecimal preco,
		
		@NotNull
		String titulo,
		
		@NotNull
		String isbn,
		
		@NotNull
		int paginas,
		
		@NotNull
		String sinopse,
		
		@NotNull
		String codigoBarra,
		
		@Valid @NotNull DadosCadastroDimensao dimensoes,

		@NotNull
		String editora,
				
		@NotNull
		String edicao,
				
		@Valid @NotNull List<DadosCadastroImagem> imagem,
		@Valid @NotNull List<DadosCadastroAutor> autor,
		@Valid @NotNull List<DadosCadastroCategoria> categoria
		
											) {

}