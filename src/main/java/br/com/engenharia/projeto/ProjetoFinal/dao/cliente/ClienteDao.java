package br.com.engenharia.projeto.ProjetoFinal.dao.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosDetalhamentoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.ClienteNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteDao implements RepositorioDeCliente {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	@Autowired
	private EntregaRepository entregaRepository;
		
	public ClienteDao(ClienteRepository repository, CartaoRepository cartaoRepository,
			CobrancaRepository cobrancaRepository, EntregaRepository entregaRepository) {
		
		this.clienteRepository = repository;
		this.cartaoRepository = cartaoRepository;
		this.cobrancaRepository = cobrancaRepository;
		this.entregaRepository = entregaRepository;
	}
	
	public ClienteDao(ClienteRepository repository) {		
		this.clienteRepository = repository;
	}
	
	public ClienteDao() {		
		
	}

	@Override
	public void salvar(Cliente entidade) {
		this.clienteRepository.save(entidade);
	}

	@Override
	public void alterarCliente(Long id, DadosAtualizacaoCliente dados) {
		Optional<Cliente> opDataBaseCliente = clienteRepository.findById(id);
		
		if(opDataBaseCliente.isPresent()) {
			Cliente clienteUpdate = opDataBaseCliente.get();
			
			if(dados.nome() != null) {
				clienteUpdate.setNome(dados.nome());
			}
			
			if(dados.genero() != null) {
				clienteUpdate.setGenero(dados.genero());
			}
			
			if(dados.nascimento() != null) {
				clienteUpdate.setNascimento(dados.nascimento());
			}
			
			if(dados.email() != null) {
				clienteUpdate.setEmail(dados.email());
			}
			
			if(dados.ddd() != null) { 
				clienteUpdate.getTelefone().setDdd(dados.ddd());
			}
			
			if(dados.telefone() != null) {
				clienteUpdate.getTelefone().setTelefone(dados.telefone());
			}
			
			if(dados.tipo() != null) {
				clienteUpdate.getTelefone().setTipoTelefone(dados.tipo());
			}
		}
	}
	
	@Override
	public void alterarSenha(Long id, String senhaCriptografada) {
		Optional<Cliente> opDataBaseSenha = clienteRepository.findById(id);
		
		if(opDataBaseSenha.isPresent()) {
			Cliente clienteUpdate = opDataBaseSenha.get();
			clienteUpdate.CriptografarSenha(senhaCriptografada);
		}
	}

	@Override
	public Optional<Cliente> consultar(Cliente entidade) {
		
		Optional<Cliente> cpfVerifica = clienteRepository.findByCpf(entidade.getCpf());
		if(cpfVerifica.isPresent()) {
			throw new ClienteNaoEncontradoExcecao("Cpf cadastrado anteriormente");
		}
		return cpfVerifica;
	}

	@Override
	public Page pegaTodosClientes(Pageable paginacao) {
		return clienteRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoCliente::new);
	}
	
	@Override
	public Cliente pegaClienteAtivo(Long id) {
		Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id);
		if(cliente.getAtivo() != true) {
			throw new ClienteNaoEncontradoExcecao("Cliente inativo");
		}
		
		return cliente;
	}
	
	@Override
	@Transactional
	public void deletar(Long id) {
		
		try {
			
			Optional<Cliente> clienteExiste = clienteRepository.findById(id);
			if(clienteExiste.isEmpty()) {
				throw new IllegalArgumentException("Id incorreto");
			}
						
			cobrancaRepository.deleteByCliente_Id(id);
		    entregaRepository.deleteByCliente_Id(id);
		    cartaoRepository.deleteByCliente_Id(id);
			clienteRepository.deleteById(id);
			
		} catch (Exception e) {
	        throw new RuntimeException("Falha ao excluir o cliente e suas entidades relacionadas", e.getCause());
		}
	}
	
	@Override
	public Optional<Cliente> verificaExistenciaClienteId(Long id){
		Optional<Cliente> existeCliente = clienteRepository.findById(id);
		return existeCliente;
	}
	
	@Override
	public Cliente recuperaClientePelo(Long id) {
	    return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id do cliente não existe"));
	}
}