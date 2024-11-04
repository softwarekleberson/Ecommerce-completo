package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarImagem(
		
		@NotNull
		Long idLivro,
		
		@NotNull
		Long idImagem,
		
		String url
								  ) {

}
