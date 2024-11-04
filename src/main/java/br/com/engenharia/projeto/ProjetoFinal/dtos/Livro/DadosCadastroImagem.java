package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroImagem(
			
		Long idLivro,
		
		@NotNull
		String url
								  ) {
}
