package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;

public record DadosUpdateLivro(
		
		Long id,
	    String titulo,
	    String primeiraImagem,
	    String autor,
	    BigDecimal preco
		
		) {

	public DadosUpdateLivro(DadosDetalhamentoLivro livro) {
		this(livro.id(),livro.titulo(), livro.primeiraImagem(),
			 livro.autor(), livro.preco());
	}
}