package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarCategoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.CategoriaNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.RepositorioDeCategoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.CategoriaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class CategoriaDao implements RepositorioDeCategoria{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public CategoriaDao(CategoriaRepository categoriaRepository, LivroRepository livroRepository) {
		this.categoriaRepository = categoriaRepository;
		this.livroRepository = livroRepository;
	}
	
	@Override
	public void salvar(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}

	@Override
	public void alterar(List<DadosAtualizarCategoria> dados) {
		for(DadosAtualizarCategoria dado : dados) {
	        Optional<Categoria> optCategoria = categoriaRepository.findById(dado.idCategoria());
	        Optional<Livro> optLivro = livroRepository.findById(dado.idLivro());
	        
	        if(optCategoria.isEmpty() || optLivro.isEmpty()) {
	            throw new CategoriaNaoEncontradoExcecao("Id da categoria ou id livro incorreto");
	        }
	        
	        Categoria categoria = optCategoria.get();
	        if(dado.nome() != null) {
	        	categoria.setCategoria(dado.nome());
	        }
	        
	        categoriaRepository.save(categoria);
		}
	}

	@Override
	public void excluir(Long livroId, Long idCategoria) {
		Optional<Categoria> optCategoria = categoriaRepository.findById(idCategoria);
		Optional<Livro> optLivro = livroRepository.findById(livroId);
		
		if(optCategoria.isEmpty() || optLivro.isEmpty()) {
			throw new CategoriaNaoEncontradoExcecao("Id da categoria ou id livro incorreto");
		}
		
		categoriaRepository.deleteById(idCategoria);
	}
}