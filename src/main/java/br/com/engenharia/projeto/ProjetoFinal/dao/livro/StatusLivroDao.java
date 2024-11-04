package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro.RepositorioDeInativacao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro.StatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.StatusLivroRepository;

@Service
public class StatusLivroDao implements RepositorioDeInativacao{

	@Autowired
	private StatusLivroRepository repository;
	
	public StatusLivroDao(StatusLivroRepository repository) {
		this.repository = repository;
	}

	@Override
	public void salvar(StatusLivro inativacao) {
		this.repository.save(inativacao);
	}
}