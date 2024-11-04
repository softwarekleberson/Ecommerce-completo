package br.com.engenharia.projeto.ProjetoFinal.dtos.pedido;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;

public record DadosDetalhamentoPedido(
    
	Long id,
	LocalDate pedidoRealizado,
	String codigoPedido,
	StatusPedido statusPedido
									) {
	
	
	public DadosDetalhamentoPedido(Pedido pedido) {
		this(pedido.getId(),
			 pedido.getPedidoRealizado(),
			 pedido.getCodigoPedido(),
			 pedido.getStatusPedido());
	}
}