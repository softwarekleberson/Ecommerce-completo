package br.com.engenharia.projeto.ProjetoFinal.dao.endereco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosAtualizacaoCobrancas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosDetalhamentoCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.CobrancaNaoEncontradaExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeCobranca;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;

@Service
public class CobrancaDao implements RepositorioDeCobranca{

	@Autowired
	private CobrancaRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public CobrancaDao(CobrancaRepository repository, ClienteRepository clienteRepository) {
		this.repository = repository;
		this.clienteRepository = clienteRepository;
	}

	@Override
	public void salvar(Cobranca cobranca) {
		atualizarCobrancaPrincipal(cobranca);
		this.repository.save(cobranca);
	}

	@Override
	public DadosDetalhamentoCobranca salvarNovaCobranca(Cobranca cobranca) {
		Optional<Cliente> cliente = clienteRepository.findById(cobranca.getCliente().getId());
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Id do cliente incorreto");
		}
		
		cobranca.setCliente(cobranca.getCliente().getId());
		atualizarCobrancaPrincipal(cobranca);
		repository.save(cobranca);
		return new DadosDetalhamentoCobranca(cobranca);
	}

	@Override
	public Cobranca alterar(Long cobrancaId, DadosAtualizacaoCobrancas dados) {
		
		Optional<Cobranca> opDataBaseCobranca = repository.findById(cobrancaId);
		
		if(opDataBaseCobranca.isEmpty()) {
			throw new CobrancaNaoEncontradaExcecao("Id cliente ou id cobranca incorreto");
		}
		
		else {
			
			if(opDataBaseCobranca.isPresent()) {
				Cobranca cobranca = opDataBaseCobranca.get();
				
				if(dados.logradouroCobranca() != null) {
					cobranca.setLogradouro(dados.logradouroCobranca());
				}
				
				if(dados.receptorCobranca() != null) {
					cobranca.setReceptor(dados.receptorCobranca());
				}
				
				if(dados.principal() == true || dados.principal() == false) {
					cobranca.setPrincipal(dados.principal());
					verificaCobrancaPrincipalClienteUpdate(cobranca);
				}
				
				if(dados.numeroCobranca() != null) {
					cobranca.setNumero(dados.numeroCobranca());
				}
				
				if(dados.bairroCobranca() != null) {
					cobranca.setBairro(dados.bairroCobranca());
				}
				
				if(dados.cepCobranca() != null) {
					cobranca.setCep(dados.cepCobranca());
				}
				
				if(dados.observacaoCobranca() != null) {
					cobranca.setObservacao(dados.observacaoCobranca());
				}
				
				if(dados.tipoLogradouroCobranca() != null) {
					cobranca.setLogradouro(dados.tipoLogradouroCobranca());
				}
				
				if(dados.tipoResidenciaCobranca() != null) {
					cobranca.setTipoResidencia(dados.tipoResidenciaCobranca());
				}
				
				if(dados.cidadeCobranca() != null) {
					cobranca.getCidade().setCidade(dados.cidadeCobranca());
				}
				
				if(dados.estadoCobranca() != null) {
					cobranca.getCidade().getEstado().setEstado(dados.estadoCobranca());
				}
				
				if(dados.paisCobranca() != null) {
					cobranca.getCidade().getEstado().setPais(dados.paisCobranca());
				}
				
				repository.save(cobranca);
				return cobranca;		
			}
		}
		return null;
	}

	@Override
	public Page<DadosDetalhamentoCobranca> listarEnderecosCobrancaDoCliente(Long clienteId, Pageable pageable) {
		Page<Cobranca> cobrancas = repository.findByCliente_Id(clienteId, pageable);	        
	    if(cobrancas.isEmpty()) {
	    	throw new CobrancaNaoEncontradaExcecao("Id incorreto");
	    }
		return cobrancas.map(DadosDetalhamentoCobranca::new);
	}

	@Override
	public void excluir(Long idCobranca) {
		
		Optional<Cobranca> cobranca =  repository.findById(idCobranca);
		
		if(cobranca.isEmpty()) {
			throw new CobrancaNaoEncontradaExcecao("Id cliente ou cobrança incorreto");
		}
		
		else {
			repository.deleteById(idCobranca);
		}
	}
	
	private void verificaCobrancaPrincipalClienteUpdate(Cobranca entidade) {
	    if (entidade.isPrincipal()) {
	        repository.atualizarCobrancasNaoPrincipalClienteExceptIdAndPrincipal(entidade.getCliente().getId(), entidade.getId());
	    }
	}
	
	private void atualizarCobrancaPrincipal(Cobranca cobranca) {
		if(cobranca.isPrincipal() == true) {
			Long idCliente = cobranca.getCliente().getId();
			repository.atualizarCobrancaPrincipal(idCliente);
		}
	}
}