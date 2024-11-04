package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroDimensao(
		
		@NotNull
		double altura,
		
		@NotNull
		double largura,
		
		@NotNull
		double peso,
		
		@NotNull
		double profundidade
		
								) {

}
