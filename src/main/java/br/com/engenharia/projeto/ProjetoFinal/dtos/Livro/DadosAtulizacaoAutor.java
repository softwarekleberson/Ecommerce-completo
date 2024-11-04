package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtulizacaoAutor(
		
		@NotNull
		Long idLivro,
		
		@NotNull
		Long idAutor,
		
		String nome
									) {
}
