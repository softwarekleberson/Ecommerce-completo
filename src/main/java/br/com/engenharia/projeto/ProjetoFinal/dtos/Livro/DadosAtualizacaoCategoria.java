package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
		
		@NotNull
		Long idLivro,
		String nome
										) {
}
