package br.com.engenharia.projeto.ProjetoFinal.dtos.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.DevolucaoFoiPedidaOUNAO;

public record DadosDetalhamentoItem(
		
		Long id,
		Long idLivro,
		Long idPedido,
		int quantidade,
		LocalDate dataPedido,
		String nome,
		String primeiraImagem,
		BigDecimal precoUnitario,
		BigDecimal subtotal,
		String codigoPedido
		
		) {

	public DadosDetalhamentoItem(Item item) {
		this(item.getId(),
			 item.getLivro().getId(),
			 item.getPedido().getId(),
		     item.getQuantidade(),
		     item.getPedido().getPedidoRealizado(),
		     item.getLivro().getTitulo(),
		     item.getLivro().getImagens().stream()
		     .findFirst().map(Imagens::getUrl).orElse(null),
			 item.getPrecoUnitario(),
			 item.getSubtotal(),
			 item.getPedido().getCodigoPedido());
	}
}