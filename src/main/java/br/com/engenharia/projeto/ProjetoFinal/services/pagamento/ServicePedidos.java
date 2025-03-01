package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusEntrega;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;

@Service
public class ServicePedidos {

	public static List<Pedido> listaPedidos(Long clienteId, PedidoRepository pedidoRepository) {
		
		System.out.println("Service Pedido");
		List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
		List<Pedido> pedidosNaoPagos = new ArrayList<>();
		for(Pedido pedido : pedidos) {
			if(!pedido.isPago()) {
				pedido.modificarStatusEntrega(StatusEntrega.EM_SEPARACAO);
				pedidosNaoPagos.add(pedido);				
			}	
		}
		
	    if (pedidosNaoPagos.isEmpty()) {
	        throw new ValidacaoPagamentoServiceException("Nenhum pedido não pago encontrado para o cliente com ID " + clienteId);
	    }
		
		return pedidosNaoPagos;
	}
}
