package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarImagem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.ImagemNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.RepositorioDeImagensProduto;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.ImagensProdutoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class ImagemProdutoDao implements RepositorioDeImagensProduto{

	@Autowired
	private ImagensProdutoRepository imagensProdutoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public ImagemProdutoDao(ImagensProdutoRepository imagensProdutoRepository, LivroRepository livroRepository ) {
		this.imagensProdutoRepository = imagensProdutoRepository;
		this.livroRepository = livroRepository;
	}
	
	@Override
	public void salvar(Imagens imagens) {
		imagensProdutoRepository.save(imagens);
	}

	@Override
	public void alterar(List<DadosAtualizarImagem> dados) {
		for(DadosAtualizarImagem dado : dados) {
			Optional<Livro> optLivro = livroRepository.findById(dado.idLivro());
			Optional<Imagens> optImagens = imagensProdutoRepository.findById(dado.idImagem());

			if(optLivro.isEmpty() || optImagens.isEmpty()) {
	            throw new ImagemNaoEncontradoExcecao("Id da categoria ou id livro incorreto");
			}
			
			Imagens imagems = optImagens.get();
			if(dado.url() != null) {
				imagems.setUrl(dado.url());
			}
			
			imagensProdutoRepository.save(imagems);
		}
	}

	@Override
	public void excluir(Long livroId, Long idImagem) {
		Optional<Livro> optLivro = livroRepository.findById(livroId);
		Optional<Imagens> optImagens = imagensProdutoRepository.findById(idImagem);
		
		if(optLivro.isEmpty() || optImagens.isEmpty()) {
			throw new ImagemNaoEncontradoExcecao("Id da imagem ou id livro incorreto");
		}
		
		imagensProdutoRepository.deleteById(idImagem);
	}
}
