package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCategoria(
		
		@NotNull
		Long idLivro,
		
		@NotNull
		Long idCategoria,
		
		String nome
		
									) {
}
