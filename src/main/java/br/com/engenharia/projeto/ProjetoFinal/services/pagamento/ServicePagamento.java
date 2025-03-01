package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosPagmento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cartaoFake.FakeRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.EstoqueRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pagamento.PagamentoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ServicePagamento {

	private final EntregaRepository entregaRepository;
	private final CobrancaRepository cobrancaRepository;
	private final CartaoRepository cartaoRepository;
	private final PedidoRepository pedidoRepository;
	private final CupomRepositroy cupomRepository;
	private final PagamentoRepository pagamentoRepository;
	private final EstoqueRepository estoqueRepository;
	private final RepositorioDeLog repositorioDeLog;
	private final FakeRepository fakeRepository;
	
	public ServicePagamento(
			EntregaRepository entregaRepository,
			CobrancaRepository cobrancaRepository,
			CartaoRepository cartaoRepository,
			PedidoRepository pedidoRepository,
			CupomRepositroy cupomRepository,
			PagamentoRepository pagamentoRepository,
			EstoqueRepository estoqueRepository,
			RepositorioDeLog repositorioDeLog,
			FakeRepository fakeRepository) {
		
		this.entregaRepository = entregaRepository;
		this.cobrancaRepository = cobrancaRepository;
		this.cartaoRepository = cartaoRepository;
		this.pedidoRepository = pedidoRepository;
		this.cupomRepository = cupomRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.estoqueRepository = estoqueRepository;
		this.repositorioDeLog = repositorioDeLog;
		this.fakeRepository = fakeRepository;
	}

	@Transactional
	public DadosPagmento validarDadosDoPagamento(@Valid DadosCadastroPagamento dados, Long clienteId) {
		
		var entrega = ServiceEntrega.verificaExistenciaEntrega(entregaRepository, clienteId);
		var cobranca = ServiceCobranca.verificaExistenciaCobranca(cobrancaRepository, clienteId);
		BigDecimal valorTotalPedido = ServiceValorTotal.verificaValorPedido(clienteId, pedidoRepository);
		
		List<Pedido> pedidos = ServicePedidos.listaPedidos(clienteId, pedidoRepository);
		List<Cartao> cartoes = ServiceValidaCartao.validaCartao(dados.idCartao1(), dados.idCartao2(), cartaoRepository);
		List<Cupom> cupons = ServiceCupom.verificaInformacoesSobreCupom(dados.cupom1(), dados.cupom2(), cupomRepository);
		ServiceCartoesPermitidos.checarQuantidadeCartoesPermitida(valorTotalPedido, cartoes, dados.idCartao1(), dados.idCartao2(), fakeRepository);
		
		Pagamento pagamento = new Pagamento(LocalDateTime.now(),
											entrega.get(),
											cobranca.get(),
											valorTotalPedido,
											pedidos,
											cartoes,
											cupons,
											StatusCompra.EM_PROCESSAMENTO);
		
		pagamentoRepository.save(pagamento);
		pagamentoRepository.flush();
		
		ServiceFakePamento.apiDePagamentoFake(pagamento, 
											  dados.idCartao1(),
											  dados.idCartao2(),
											  dados.cupom1(),
											  dados.cupom2(), 
			    							  cartaoRepository, 
			    							  fakeRepository, 
			    							  cupomRepository, 
			    							  pagamentoRepository
			    							 );
		
		ServicePagamentoPedido.associarPagamentoAPedidos(pagamento, pedidos, pedidoRepository);
		ServiceBaixaEstoque.baixaNoEstoque(pedidos, estoqueRepository);
		ServiceExclusaoCartao.cartaoSeraSalvo(dados.salvarCartao(), cartoes, cartaoRepository);
		
		Log log = new Log(clienteId);
		repositorioDeLog.save(log);
		
		return new DadosPagmento(pagamento);
	}
}