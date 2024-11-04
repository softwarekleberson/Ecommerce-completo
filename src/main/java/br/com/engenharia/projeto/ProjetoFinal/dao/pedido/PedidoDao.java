package br.com.engenharia.projeto.ProjetoFinal.dao.pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.PedidoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;

@Service
public class PedidoDao implements RepositorioDePedido{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public PedidoDao(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	@Override
	public void salvar(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	@Override
	public Optional<Pedido> verificaCodigoPedido(String codigoPedido) {
		Optional<Pedido> pedido = pedidoRepository.findByCodigoPedido(codigoPedido);
		return pedido;
	}

	@Override
	public Pedido devolvePedidoPeloCodigo(String codigoPedido) {
		var pedido = pedidoRepository.findByCodigoPedido(codigoPedido);
		if(pedido == null) {
			throw new PedidoNaoEncontradoExcecao("Codigo pedido incorreto");
		}
		return pedido.get();
	}

	@Override
	public Page<DadosDetalhamentoPedido> listarPedidosCliente(Long clienteId, Pageable pageable) {
		 Page<Pedido> pedidosPage = pedidoRepository.findByCliente_Id(clienteId, pageable);	        
	     System.out.println("zzzzzzz");
		 if(pedidosPage.isEmpty()) {
	    	 throw new PedidoNaoEncontradoExcecao("Id incorreto");
	     }
		 return pedidosPage.map(DadosDetalhamentoPedido::new);
	}

	@Override
	public List<Pedido> encontrarPedidosNaoPagosAposDataLimite(LocalDate dataLimite) {
		return pedidoRepository.findByPagoFalseAndPedidoRealizadoBefore(dataLimite);
	}
	
	public void excluir(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
}