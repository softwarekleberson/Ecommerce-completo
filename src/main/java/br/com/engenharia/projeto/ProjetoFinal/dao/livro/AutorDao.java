package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtulizacaoAutor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.AutorNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.RepositorioDeAutor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.AutorRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class AutorDao implements RepositorioDeAutor{

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public AutorDao(AutorRepository autorRepository, LivroRepository livroRepository) {
		this.autorRepository = autorRepository;
		this.livroRepository = livroRepository;
	}

	@Override
	public void salvar(Autor autor) {
		this.autorRepository.save(autor);
	}

	@Override
	public void alterar(List<DadosAtulizacaoAutor> dados) {
		
		for(DadosAtulizacaoAutor dado : dados) {	
			Optional<Livro> optLivro = livroRepository.findById(dado.idLivro());
			Optional<Autor> optAutor = autorRepository.findById(dado.idAutor());
			
			if(optLivro.isEmpty() || optAutor.isEmpty()) {
	            throw new AutorNaoEncontradoExcecao("Id do autor ou id livro incorreto");
			}
			
			Autor autor = optAutor.get();
			if(dado.nome() != null) {
				autor.setAutor(dado.nome());
			}
			autorRepository.save(autor);
		}
	}

	@Override
	public void excluir(Long livroId, Long idAutor) {
		Optional<Livro> existeAutor = livroRepository.findById(livroId);
		Optional<Autor> autorExiste = autorRepository.findById(idAutor);
		
		if(existeAutor.isEmpty() || autorExiste.isEmpty()) {
			throw new AutorNaoEncontradoExcecao("Id incorreto do livro ou autor");
		}
		
		autorRepository.deleteById(idAutor);
	}
}