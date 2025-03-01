package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;

@Service
public class ServiceValorTotal {

	public static BigDecimal verificaValorPedido(Long clienteId, PedidoRepository pedidoRepository) {
		System.out.println("Service valor total");
		return pedidoRepository.findByClienteIdAndPagoFalse(clienteId).stream()
				.map(Pedido::atualizarValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
