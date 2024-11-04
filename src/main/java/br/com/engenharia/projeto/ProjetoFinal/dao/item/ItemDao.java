package br.com.engenharia.projeto.ProjetoFinal.dao.item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagos;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.PedidoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido.ItemPedidoRepository;

@Service
public class ItemDao implements RepositorioDeItem{

	@Autowired
	private ItemPedidoRepository repository;
	
	public ItemDao(ItemPedidoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Item item) {
		repository.save(item);
	}

	@Override
	public DadosDetalhamentoItem upadate(Long id, DadosAtualizacaoItem dados) {
		Optional<Item> opDataItem = repository.findById(id);
		int quantidade = dados.quantidade();
		if(opDataItem.isPresent()) {
			Item item = opDataItem.get();
			
			if(quantidade > 0) {
				item.setQuantidade(dados.quantidade());
				BigDecimal upateSubtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
				item.setSubtotal(upateSubtotal);
			
			}else {
				throw new PedidoNaoEncontradoExcecao("Quantidade inferior ao permitido");
			}
			
			repository.save(item);
			return new DadosDetalhamentoItem(item);
		}
		return null;
	}

	@Override
	public void deletar(Long id) {
		Optional<Item> item = repository.findById(id);
		if(item.isEmpty()) {
			throw new PedidoNaoEncontradoExcecao("Item não encontrado");
		}
		repository.deleteById(id);
	}

	@Override
	public Page<DadosDetalhamentoItem> listarItensDoCliente(Long clienteId, Pageable pageable) {
		List<DadosDetalhamentoItem> itens = repository.buscarItensDetalhadosPorClienteId(clienteId);

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), itens.size());

	    return new PageImpl<>(itens.subList(start, end), pageable, itens.size());
	}

	@Override
	public Page<DadosDetalhamentoItensPagos> pedidosPagos(Long clienteId, Pageable pageable) {
		List<DadosDetalhamentoItensPagos> itens = repository.buscarItensPagos(clienteId);

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), itens.size());

	    return new PageImpl<>(itens.subList(start, end), pageable, itens.size());
	}

	@Override
	public Page<DadosDetalhamentoItensPagos> listarTodosOsPedidos(Pageable pageable) {
	    return repository.buscarTodosOsItensPagos(pageable);
	}
}