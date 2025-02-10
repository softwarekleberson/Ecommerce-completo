package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem;

public class ImagemDaoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ImagemDaoExcecao(String message) {
		super(message);
	}
}
