package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtulizacaoAutor;

public interface RepositorioDeAutor {

	void salvar(Autor autor);
	public void alterar(List<DadosAtulizacaoAutor> dados);
	void excluir(Long livroId, Long idAutor);
}
