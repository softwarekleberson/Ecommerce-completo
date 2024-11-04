package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarImagem;

public interface RepositorioDeImagensProduto {

	void salvar(Imagens imagens);
	public void alterar(List<DadosAtualizarImagem> dados);
	void excluir(Long livroId, Long idImagem);

}
