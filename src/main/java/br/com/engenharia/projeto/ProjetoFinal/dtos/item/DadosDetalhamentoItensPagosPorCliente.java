package br.com.engenharia.projeto.ProjetoFinal.dtos.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.DevolucaoFoiPedidaOUNAO;

public interface DadosDetalhamentoItensPagosPorCliente {
	 
	 Long getId();
	 Long getIdLivro();
	 Long getIdPedido();
	 boolean isPago();
	 LocalDate getEntregue();
	 int getQuantidade();
	 LocalDate getDataPedido();
	 String getNome();
	 String getPrimeiraImagem();
	 StatusCompra getStatus();
	 BigDecimal getPrecoUnitario();
	 BigDecimal getSubtotal();
	 String getCodigoPedido();
	 DevolucaoFoiPedidaOUNAO getTrocaDevolucao();
	
}
