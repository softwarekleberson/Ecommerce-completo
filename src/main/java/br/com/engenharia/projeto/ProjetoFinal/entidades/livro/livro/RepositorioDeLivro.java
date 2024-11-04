package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivroCompleto;

public interface RepositorioDeLivro {

	void salvar(Livro livro);
	Page<DadosDetalhamentoLivro> listarLivros(Pageable pageable);
	public DadosDetalhamentoLivroCompleto listarLivroExpecifico(Long id);
	void excluir(Long livroId);
	void exclusao(Livro exclusaoLogica);
	DadosDetalhamentoLivro alterar(DadosAtualizarLivro dados);
	public Optional<Livro> isbnCadastradoAnteriormente(String isbn);
	Optional<Livro> buscarLivroPeloId(Long livroId);
}
