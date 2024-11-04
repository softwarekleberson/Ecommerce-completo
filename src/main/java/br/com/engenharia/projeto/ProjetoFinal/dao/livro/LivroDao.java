package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarDimensao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivroCompleto;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.LivroNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.AutorRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.CategoriaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.ImagensProdutoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class LivroDao implements RepositorioDeLivro{

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	AutorRepository autorRepository;
	
	@Autowired
	ImagensProdutoRepository produtoRepository;

	
	public LivroDao(LivroRepository livroRepository,
					CategoriaRepository categoriaRepository,
					AutorRepository autorRepository,
					ImagensProdutoRepository produtoRepository)
																{
		
		this.livroRepository = livroRepository;
		this.categoriaRepository = categoriaRepository;
		this.autorRepository = autorRepository;
		this.produtoRepository = produtoRepository;
		
	}
	
	public LivroDao(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public LivroDao() {
		
	}

	@Override
	public void salvar(Livro livro) {
		this.livroRepository.save(livro);
	}

	@Override
	public Page<DadosDetalhamentoLivro> listarLivros(Pageable pageable) {
		Page<Livro> livros = livroRepository.findAllByAtivoTrue(pageable);
		if (livros.isEmpty()) {
		   throw new LivroNaoEncontradoExcecao("Não há livros disponíveis");
		}
		    
		Page<DadosDetalhamentoLivro> dadosDetalhamentoLivros = livros.map(DadosDetalhamentoLivro::new);		    
		return dadosDetalhamentoLivros;
	}

	@Override
	public DadosDetalhamentoLivro alterar(DadosAtualizarLivro dados) {
		Optional<Livro> optDataBaseLivro = livroRepository.findById(dados.id());
		
		if(optDataBaseLivro.isPresent()) {
			Livro livro = optDataBaseLivro.get();
			
			if(dados.codigoBarra() != null) {
				livro.setCodigoBarra(dados.codigoBarra());
			}
			
			if(dados.data() != null) {
				livro.setData(dados.data());
			}
			
			if(dados.preco() != null) {
				livro.setPreco(dados.preco());
			}
			
			DadosAtualizarDimensao livroDimensoes = dados.dimensoes();
			if(livroDimensoes != null) {
				double profundidade = dados.dimensoes().profundidade();
				if(profundidade > 0) {
					livro.getDimensoes().setProfundidade(dados.dimensoes().profundidade());
				}
			}
			
			if(livroDimensoes != null) {
				double largura = dados.dimensoes().largura();
				if(largura > 0) {
					livro.getDimensoes().setLargura(dados.dimensoes().largura());
				}
			}
			
			if(livroDimensoes != null) {
				double altura = dados.dimensoes().altura();
				if(altura > 0) {
					livro.getDimensoes().setAltura(dados.dimensoes().altura());
				}
			}
			
			if(livroDimensoes != null) {
				double peso = dados.dimensoes().peso();
				if(peso > 0) {
					livro.getDimensoes().setPeso(dados.dimensoes().peso());
				}
			}
			
			if(dados.edicao() != null) {
				livro.setEdicao(dados.edicao());
			}
			
			if(dados.editora() != null) {
				livro.setEditora(dados.editora());
			}
			
			if(dados.isbn() != null) {
				livro.setIsbn(dados.isbn());
			}
			
			int paginas = dados.paginas();
			if(paginas > 0) {
				livro.setPaginas(dados.paginas());
			}
			
			if(dados.sinopse() != null) {
				livro.setSinopse(dados.sinopse());
			}
			
			if(dados.titulo() != null) {
				livro.setTitulo(dados.titulo());
			}
			
			if(dados.categoria() != null) {
				new CategoriaDao(categoriaRepository, livroRepository).alterar(dados.categoria());
			}
			
			if(dados.autor() != null) {
				new AutorDao(autorRepository, livroRepository).alterar(dados.autor());
			}
			
			if(dados.imagem() != null) {
				new ImagemProdutoDao(produtoRepository, livroRepository).alterar(dados.imagem());
			}
			
			livroRepository.save(livro);
			return new DadosDetalhamentoLivro(livro);
		}
		
		return null;
				
	}
	
	public BigDecimal precoUnitario(Long idLivro) {
		return livroRepository.findPrecoById(idLivro);
	}
	
	@Override
	public void excluir(Long livroId) {
		Optional<Livro> livro = livroRepository.findById(livroId);
		
		if(livro.isEmpty()) {
			throw new LivroNaoEncontradoExcecao("Id do livro incorreto");
		}
		
		var exclusaoLogica = livroRepository.getReferenceById(livroId);
		exclusao(exclusaoLogica);
	}
	
	@Override
	public void exclusao(Livro exclusaoLogica) {
		exclusaoLogica.setAtivo(false);
	}

	@Override
	public DadosDetalhamentoLivroCompleto listarLivroExpecifico(Long id) {
		var livro = livroRepository.getReferenceById(id);
		return new DadosDetalhamentoLivroCompleto(livro);
	}

	public Optional<Livro> isbnCadastradoAnteriormente(String isbn) {
		Optional<Livro> livro = livroRepository.findByisbn(isbn);
		return livro;
	}
	
	public Optional<Livro> buscarLivroPeloId(Long livroId){
		return livroRepository.findById(livroId);
	}
}