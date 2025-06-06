package br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagos;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagosPorCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface ItemPedidoRepository extends JpaRepository<Item, Long> {

	@Query("SELECT new br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem( " +
		       "i.id, l.id, p.id, i.quantidade, p.pedidoRealizado, l.titulo, " +
		       "img.url, " + 
		       "i.precoUnitario, i.subtotal, p.codigoPedido) " +
		       "FROM Item i " +
		       "JOIN i.livro l " +
		       "JOIN l.imagens img " + 
		       "JOIN i.pedido p " +
		       "JOIN p.cliente c " +
		       "WHERE c.id = :clienteId AND p.pago = false " +
		       "AND img.id = (SELECT MIN(i2.id) FROM Imagens i2 WHERE i2.livro.id = l.id)") 
	List<DadosDetalhamentoItem> buscarItensDetalhadosPorClienteId(@Param("clienteId") Long clienteId);

	
	@Query("SELECT i.id as id, l.id as idLivro, p.id as idPedido, p.pago as pago, p.entregue as entregue, " +
		       "i.quantidade as quantidade, p.pedidoRealizado as dataPedido, l.titulo as nome, " +
		       "img.url as primeiraImagem, pag.statusCompra as status, i.precoUnitario as precoUnitario, " +
		       "i.subtotal as subtotal, p.codigoPedido as codigoPedido, p.trocaDevolucao as trocaDevolucao " +
		       "FROM Item i " +
		       "JOIN i.livro l " +
		       "JOIN l.imagens img " + 
		       "JOIN i.pedido.pagamento pag " + 
		       "JOIN i.pedido p " +
		       "JOIN p.cliente c " +
		       "WHERE c.id = :clienteId AND pag.statusCompra = APROVADO " + 
		       "AND img.id = (SELECT MIN(i2.id) FROM Imagens i2 WHERE i2.livro.id = l.id)")
	List<DadosDetalhamentoItensPagosPorCliente> buscarItensPagos(@Param("clienteId") Long clienteId);


	@Query("SELECT i.id as id, l.id as idLivro, p.id as idPedido, p.pago as pago, p.entregue as entregue, " +
		       "i.quantidade as quantidade, p.pedidoRealizado as dataPedido, l.titulo as nome, " +
		       "img.url as primeiraImagem, pag.statusCompra as status, i.precoUnitario as precoUnitario, " +
		       "i.subtotal as subtotal, p.codigoPedido as codigoPedido " +
		       "FROM Item i " +
		       "JOIN i.livro l " +
		       "JOIN l.imagens img " + 
		       "JOIN i.pedido.pagamento pag " + 
		       "JOIN i.pedido p " +
		       "WHERE p.pago = true " +
		       "AND img.id = (SELECT MIN(i2.id) FROM Imagens i2 WHERE i2.livro.id = l.id)")
		Page<DadosDetalhamentoItensPagos> buscarTodosOsItensPagos(Pageable pageable);

}
