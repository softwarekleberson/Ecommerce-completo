package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroStatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro.RepositorioDeInativacao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro.StatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class ServiceAtivarInativarLivro {

	@Autowired
	private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private RepositorioDeInativacao repositorioDeInativacao;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void delecaoLogica(DadosCadastroStatusLivro dados) {
		Livro livro = livroRepository.getReferenceById(dados.idLivro());
		livro.setAtivo(false);
		
		StatusLivro inativacao = new StatusLivro(null,
											   dados.justificativa(),
											   dados.categoria(),
											   livro);
				
		repositorioDeLivro.salvar(livro);
		repositorioDeInativacao.salvar(inativacao);
	}
	
	public void ativarLogica(DadosCadastroStatusLivro dados) {
		Livro livro = livroRepository.getReferenceById(dados.idLivro());
		livro.setAtivo(true);
		
		StatusLivro ativacao = new StatusLivro(null,
				   								 dados.justificativa(),
				                                 dados.categoria(),
				                                 livro);
		repositorioDeLivro.salvar(livro);	
		repositorioDeInativacao.salvar(ativacao);

	}
}