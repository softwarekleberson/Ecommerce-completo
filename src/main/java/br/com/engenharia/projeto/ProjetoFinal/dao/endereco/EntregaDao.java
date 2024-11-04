package br.com.engenharia.projeto.ProjetoFinal.dao.endereco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosAtualizacaoEntregas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosDetalhamentoEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.EntregaNaoEncontradaExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeEntrega;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;

@Service
public class EntregaDao implements RepositorioDeEntrega{

	@Autowired
	private EntregaRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public EntregaDao(EntregaRepository repository, ClienteRepository clienteRepository) {
		this.repository = repository;
		this.clienteRepository = clienteRepository;
	}

	@Override
	public void salvar(Entrega entrega) {
		this.repository.save(entrega);
	}
	
	@Override
	public DadosDetalhamentoEntrega salvarNovoEntrega(Entrega entrega) {
		System.out.println(entrega.getCliente().getId());
		Optional<Cliente> existeCliente = clienteRepository.findById(entrega.getCliente().getId());
		
		if(existeCliente.isEmpty()) {
			throw new EntregaNaoEncontradaExcecao("Id do cliente não existe");
		}
		
		entrega.setCliente(entrega.getCliente().getId());
		atualizarEnderecoPrincipal(entrega);
		
		repository.save(entrega);
		return new DadosDetalhamentoEntrega(entrega);
		
	}

	@Override
	public Entrega alterar(Long entregaId, DadosAtualizacaoEntregas dados) {
		Optional<Entrega> opDataBaseEntrega = repository.findById(entregaId);
		
		if(opDataBaseEntrega.isEmpty()) {
			throw new EntregaNaoEncontradaExcecao("Id Entrega incorreto");
		}
		
		else {
			
			if(opDataBaseEntrega.isPresent()) {
				Entrega entrega = opDataBaseEntrega.get();
				
				if(dados.logradouroEntrega() != null) {
					entrega.setLogradouro(dados.logradouroEntrega());
				}
				
				if(dados.receptorEntrega() != null) {
					entrega.setReceptor(dados.receptorEntrega());
				}
								
				if(dados.principal() == true || dados.principal() == false) { 
					entrega.setPrincipal(dados.principal());
					verificaCobrancaPrincipalClienteUpdate(entrega);
				}
				
				if(dados.numeroEntrega() != null) {
					entrega.setNumero(dados.numeroEntrega());
				}

				if(dados.bairroEntrega() != null) {
					entrega.setBairro(dados.bairroEntrega());
				}
				
				if(dados.cepEntrega() != null) {
					entrega.setCep(dados.cepEntrega());
				}
				
				if(dados.observacaoEntrega() != null) {
					entrega.setObservacao(dados.observacaoEntrega());
				}
				
				if(dados.fraseEntregaEntrega() != null) {
					entrega.setObservacao(dados.fraseEntregaEntrega());
				}
				
				if(dados.tipoLogradouroEntrega() != null) {
					entrega.getTipoLogradouro().setTipoLogradouro(dados.tipoLogradouroEntrega());
				}
				
				if(dados.tipoResidenciaEntrega() != null) {
					entrega.getTipoResidencia().setTipoResidencia(dados.tipoResidenciaEntrega());
				}
				
				if(dados.cidadeEntrega() != null) {
					entrega.getCidade().setCidade(dados.cidadeEntrega());
				}
				
				if(dados.estadoEntrega() != null) {
					entrega.getCidade().getEstado().setEstado(dados.estadoEntrega());
				}
				
				if(dados.paisEntrega() != null) {
					entrega.getCidade().getEstado().getPais().setPais(dados.paisEntrega());
				}
				
				repository.save(entrega);
				return entrega;
			}
		}
		return null;
	}

	@Override
	public Page<DadosDetalhamentoEntrega> listarEntregasDoCliente(Long clienteId, Pageable pageable) {
		Page<Entrega> entregas = repository.findByCliente_Id(clienteId, pageable);	        
	    if(entregas.isEmpty()) {
	    	throw new EntregaNaoEncontradaExcecao("Id incorreto");
	    }
		return entregas.map(DadosDetalhamentoEntrega::new);
	}

	@Override
	public void excluir(Long idEntrega) {
		
		Optional<Entrega> entrega =  repository.findById(idEntrega);
		
		if(entrega.isEmpty()) {
			throw new EntregaNaoEncontradaExcecao("Id incorreto do cliente ou entrega");
		}else {
			repository.deleteById(idEntrega);
		}
	}
	
	private void verificaCobrancaPrincipalClienteUpdate(Entrega entidade) {
	    if (entidade.isPrincipal()) {
	        repository.atualizarEntregasNaoPrincipalClienteExceptIdAndPrincipal(entidade.getCliente().getId(), entidade.getId());
	    }
	}
	
	private void atualizarEnderecoPrincipal(Entrega entrega) {
		if(entrega.isPrincipal() == true) {
			Long idCliente = entrega.getCliente().getId();
			repository.atualizarEntregaPrincipal(idCliente);
		}
	}
}