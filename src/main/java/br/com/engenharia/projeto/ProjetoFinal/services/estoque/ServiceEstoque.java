package br.com.engenharia.projeto.ProjetoFinal.services.estoque;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroEstoque;
import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosDetalhamentoEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.RepositorioDeEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import jakarta.validation.Valid;

@Service
public class ServiceEstoque {

	@Autowired
	private RepositorioDeEstoque repositorioDeEstoque;
	
	@Autowired
	private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private LivroRepository repository;
	
	public DadosDetalhamentoEstoque criar(@Valid DadosCadastroEstoque dados) {

		Optional<Livro> existeLivro = repository.findById(dados.idLivro());
		if(existeLivro.isEmpty()) {
			throw new IllegalArgumentException("Id do livro incorreto");
		}
		
		Livro livro = repository.getReferenceById(dados.idLivro());
		livro.setAtivo(true);
		repositorioDeLivro.salvar(livro);
		
	    Estoque estoque = new Estoque(dados);
	    repositorioDeEstoque.salvar(estoque);
	    return new DadosDetalhamentoEstoque(estoque);
	}
}
