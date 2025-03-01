package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;

@Service
public class ServicePagamentoPedido {

	public static void associarPagamentoAPedidos(Pagamento pagamento, List<Pedido> pedidos,
			PedidoRepository pedidoRepository) {
		
			System.out.println("Service Pagameto pedido");
			if(pagamento.getStatusCompra() == StatusCompra.APROVADO) {
			
			for (Pedido pedido : pedidos) {
				pedido.setPagamento(pagamento);
				pedido.setPago(true);
				pedido.setStatusPedido(StatusPedido.PAGO);
				pedidoRepository.save(pedido);
			}
		    pedidoRepository.flush();
		}
	}
}
