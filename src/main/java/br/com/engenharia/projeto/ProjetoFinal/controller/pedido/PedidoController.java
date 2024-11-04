package br.com.engenharia.projeto.ProjetoFinal.controller.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dao.item.RepositorioDeItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagos;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho.ItemRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedido;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedidoUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private ServicePedido insert;
	
	@Autowired
	private ServicePedidoUpdate update;
	
	@Autowired
	private RepositorioDeItem repositorioDeItem;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostMapping("{livroId}/{clienteId}")
	public ResponseEntity cadastrar(@PathVariable Long clienteId, @PathVariable Long livroId, @RequestBody @Valid DadosCadastroPedido dados, UriComponentsBuilder uriBuilder) {
		var dto = insert.criar(dados, clienteId, livroId);
	    var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoItem>> listarItensPorCliente(
	        										   @PathVariable Long clienteId,
	        										   @PageableDefault(size = 15, sort = "id") Pageable pageable) {

	    Page<DadosDetalhamentoItem> itens = repositorioDeItem.listarItensDoCliente(clienteId, pageable);
	    
	    return ResponseEntity.ok(itens);
	}

	@GetMapping("pagos/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoItensPagos>> listarItensPagos(
	        										   @PathVariable Long clienteId,
	        										   @PageableDefault(size = 15, sort = "id") Pageable pageable) {
		
	    Page<DadosDetalhamentoItensPagos> itens = repositorioDeItem.pedidosPagos(clienteId, pageable);
	    
	    return ResponseEntity.ok(itens);
	}
	
	@PutMapping("itens/produto/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoItem dados) {
		DadosDetalhamentoItem detalhamentoItem = update.atualizar(dados, id);
        return ResponseEntity.ok(detalhamentoItem);
	}
	
	@DeleteMapping("itens/produto/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		
		Optional<Item> item = itemRepository.findById(id);
		Pedido pedido = item.get().getPedido();
		
		repositorioDeItem.deletar(id);
		pedidoRepository.delete(pedido);
		return ResponseEntity.noContent().build();
	}
}