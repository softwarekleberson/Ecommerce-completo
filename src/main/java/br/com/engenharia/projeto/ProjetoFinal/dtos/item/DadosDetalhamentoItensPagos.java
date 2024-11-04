package br.com.engenharia.projeto.ProjetoFinal.dtos.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.DevolucaoFoiPedidaOUNAO;

public record DadosDetalhamentoItensPagos(
		Long id,
		Long idLivro,
		Long idPedido,
		boolean pago,
		LocalDate entregue,
		int quantidade,
		LocalDate dataPedido,
		String nome,
		String primeiraImagem,
		StatusCompra status,
		BigDecimal precoUnitario,
		BigDecimal subtotal,
		String codigoPedido,
	    DevolucaoFoiPedidaOUNAO trocaDevolucao 
) {

	public DadosDetalhamentoItensPagos(Item item) {
		this(
			item.getId(),
			item.getLivro().getId(),
			item.getPedido().getId(),
			item.getPedido().isPago(),
			item.getPedido().getEntregue(),
			item.getQuantidade(),
			item.getPedido().getPedidoRealizado(), 
			item.getLivro().getTitulo(),
			item.getLivro().getImagens().stream()
				.findFirst()
				.map(Imagens::getUrl)
				.orElse(null),
			item.getPedido().getPagamento().getStatusCompra(),
			item.getPrecoUnitario(),
			item.getSubtotal(),
			item.getPedido().getCodigoPedido(),
			item.getPedido().getTrocaDevolucao()
		);
	}
}
