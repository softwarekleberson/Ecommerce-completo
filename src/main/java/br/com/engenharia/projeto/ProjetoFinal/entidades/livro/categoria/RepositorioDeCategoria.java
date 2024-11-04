package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarCategoria;

public interface RepositorioDeCategoria {

	void salvar(Categoria categoria);
	public void alterar(List<DadosAtualizarCategoria> dados);
	void excluir(Long livroId, Long idCategoria);
}
