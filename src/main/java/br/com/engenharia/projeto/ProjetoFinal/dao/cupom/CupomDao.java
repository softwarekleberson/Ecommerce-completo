package br.com.engenharia.projeto.ProjetoFinal.dao.cupom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.CupomNaoEcontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.RepositorioDeCupom;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;

@Service
public class CupomDao implements RepositorioDeCupom{

	@Autowired
	private CupomRepositroy repository;
	
	public CupomDao(CupomRepositroy repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Cupom cupom) {
		this.repository.save(cupom);
	}
	
	public Page listarCuponsDosClientes(Long clienteId, Pageable pageable) {
		 Page<Cupom> cuponsPage = repository.listarCupomAtivo(clienteId, pageable);	        
	     if(cuponsPage.isEmpty()) {
	    	 throw new CupomNaoEcontradoExcecao("Id incorreto");
	     }
		 return cuponsPage.map(DadosDetalhamentoCupom::new);
	}

	@Override
	public Optional<Cupom> encontrarCupomPorCodigo(String codigoCupom) {
		Optional<Cupom> cupom = repository.buscarCupomPorId(codigoCupom);
		return cupom;
	}
}
