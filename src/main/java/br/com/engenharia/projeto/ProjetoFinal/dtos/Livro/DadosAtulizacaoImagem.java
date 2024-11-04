package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtulizacaoImagem(
		
		@NotNull
		Long idLivro,
		String url			
		
									) {
}
