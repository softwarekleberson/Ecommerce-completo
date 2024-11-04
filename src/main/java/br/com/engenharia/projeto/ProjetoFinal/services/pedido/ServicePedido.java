package br.com.engenharia.projeto.ProjetoFinal.services.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.item.RepositorioDeItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.ClienteNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.EstoqueNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.RepositorioDeEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.LivroNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.EstoqueRepository;
import jakarta.validation.Valid;

@Service
public class ServicePedido {
	
	@Autowired
	private RepositorioDePedido repositorioDePedido;
		
	@Autowired
	private RepositorioDeItem repositorioDeItem;
	
	@Autowired
	private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private RepositorioDeEstoque repositorioDeEstoque;
	
	@Autowired
	private RepositorioDeCliente repositorioDeCliente;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private RepositorioDeLog repositorioDeLog;
	
	public DadosDetalhamentoPedido criar(@Valid DadosCadastroPedido dados, Long clienteId, Long livroId) {
		
		var livro = verificaExistenciaLivro(livroId);
		var cliente = verificaExistenciaDeCliente(clienteId);
		verificaDiponibilidadeEmEstoque(dados, livroId);
			
		Pedido pedido = criarPedidoERetornarCodigoPedido(cliente);
		registrarItensPedido(pedido.getCodigoPedido(), dados.quantidade(), livro);
		
		Log log = new Log(pedido.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoPedido(pedido);
	}

	private Pedido criarPedidoERetornarCodigoPedido(Cliente cliente) {
		var codigoPedido = UUID.randomUUID().toString();
		Pedido pedido = new Pedido(null, LocalDate.now(),
								   codigoPedido, cliente,
								   StatusPedido.AGUARDANDO_PAGAMENTO,
								   DevolucaoFoiPedidaOUNAO.DEVOLUCAO_NAO_PEDIDA,
								   StatusEntrega.NAO_PAGO);
		
		repositorioDePedido.salvar(pedido);
		
		return pedido;
	}
	
	private void registrarItensPedido(String codigoPedido, int quantidade, Livro livro) {
	    Pedido pedido = repositorioDePedido.devolvePedidoPeloCodigo(codigoPedido);
	    BigDecimal precoUnitario = livro.getPreco();
	    
	    Item item = new Item(null, quantidade, precoUnitario, livro, pedido);
	    
	    BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));	    
	    item.setSubtotal(subtotal);
	    repositorioDeItem.salvar(item);
	}

	private void verificaDiponibilidadeEmEstoque(DadosCadastroPedido dados, Long livroId) {
		List<Estoque> estoque = estoqueRepository.findByLivroId(livroId);
		
		int disponivel = 0;
		for(Estoque estoques : estoque) {
			disponivel += estoques.getQuantidade();
		}
		
		System.out.println(disponivel + "em estoque");
		if(dados.quantidade() > disponivel) {
			throw new EstoqueNaoEncontradoExcecao("Quantidade requerida superior a disponivel em estoque");
		}
	}

	private Cliente verificaExistenciaDeCliente(Long clienteId) {
		var cliente = repositorioDeCliente.verificaExistenciaClienteId(clienteId);
		if(clienteId == null && cliente.isEmpty()) {
			throw new ClienteNaoEncontradoExcecao("Cliente não encontrado");
		}
		return cliente.get();
	}

	private Livro verificaExistenciaLivro(Long livroId) {
		var livro = repositorioDeLivro.buscarLivroPeloId(livroId);
		if(livroId == null && livro.isEmpty()) {
			throw new LivroNaoEncontradoExcecao("Livro não encontrado");
		}
		return livro.get();
	}
}