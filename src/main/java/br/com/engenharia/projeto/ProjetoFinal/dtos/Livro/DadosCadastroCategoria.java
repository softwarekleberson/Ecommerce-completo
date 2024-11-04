package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCategoria(
		
		Long idLivro,
		
		@NotNull
		String nome
									) {
}
