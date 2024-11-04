package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro.Categoria;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroStatusLivro(
		
		@NotNull
		Long idLivro,
		
		@NotNull
		String justificativa,
		
		@NotNull
		Categoria categoria
		
									) {
}
