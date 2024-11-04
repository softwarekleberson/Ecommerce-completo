package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.Precificacao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.RepositorioDePrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.PrecificacaoRepository;

@Service
public class PrecificacaoDao implements RepositorioDePrecificacao{

	@Autowired
	private PrecificacaoRepository precificacaoRepository;
	
	public PrecificacaoDao(PrecificacaoRepository precificacaoRepository) {
		this.precificacaoRepository = precificacaoRepository;
	}
	
	@Override
	public void salvar(Precificacao precificacao) {
		this.precificacaoRepository.save(precificacao);
	}
}