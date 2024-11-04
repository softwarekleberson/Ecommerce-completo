package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao.IstrategyValidaCartao;
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
import jakarta.validation.ValidationException;

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
	
	@Autowired
	private List<IstrategyValidaCartao> validacoes;
	
	
	public DadosDetalhamentoCartao criar(DadosCadastroCartao dados) {
		Optional<Cliente> clienteExiste = clienteRepository.findById(dados.idCliente());
		if(!clienteExiste.isPresent()) {
			throw new ValidationException("Id cliente não encontrado");
		}	
		
		validacoes.forEach(v->v.processar(dados));
		
		Cartao cartao = new Cartao(dados);
		repositorioDeCartao.salvar(cartao);	
		
		Log log = new Log(cartao.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoCartao(cartao);
	}

	public DadosDetalhamentoCartao atualizar(@Valid DadosAtualizacaoCartao dados, Long cartaoId) {
		
		Optional<Cartao> cartaoExiste = cartaoRepository.findById(cartaoId);
		if(!cartaoExiste.isPresent()) {
			throw new ValidationException("Id cartão não encontrado");
		}
		
		var cartao = repositorioDeCartao.alterar(cartaoId,dados);
		Log log = new Log(cartao.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoCartao(cartao);
	}
}