package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosDetalhamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.RepositorioDeCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ServiceCartao {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private RepositorioDeCartao repositorioDeCartao;
	
	@Autowired
	private RepositorioDeLog repositorioDeLog;
	
	public DadosDetalhamentoCartao criar(Long idCliente, DadosCadastroCartao dados) {
		Optional<Cliente> clienteExiste = clienteRepository.findById(idCliente);
		if(!clienteExiste.isPresent()) {
			throw new ValidacaoCartaoServiceException("Id cliente não encontrado");
		}	
				
		Cartao cartao = new Cartao(dados);
		cartao.setCliente(idCliente);
		repositorioDeCartao.salvar(cartao);	
		
		Log log = new Log(cartao.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoCartao(cartao);
	}

	public DadosDetalhamentoCartao atualizar(@Valid DadosAtualizacaoCartao dados, Long cartaoId, Long idUsuario) {
		
		Optional<Cartao> cartaoExiste = cartaoRepository.findById(cartaoId);
		
		if(!cartaoExiste.isPresent()) {
			throw new ValidacaoCartaoServiceException("Id cartão não encontrado");
		}
		
		if(!cartaoExiste.get().getCliente().getId().equals(idUsuario)) {
			throw new ValidacaoCartaoServiceException("Acesso negado! Este cartão não pertence ao usuário.");
		}
		
		var cartao = repositorioDeCartao.alterar(cartaoId,dados);
		Log log = new Log(cartao.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoCartao(cartao);
	}
}