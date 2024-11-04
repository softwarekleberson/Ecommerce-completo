package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAutor(
		
		Long idLivro,
		
		@NotNull
		String nome
								) {
}
