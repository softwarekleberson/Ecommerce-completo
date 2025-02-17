package br.com.engenharia.projeto.ProjetoFinal.controller.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagosPorCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho.ItemRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedido;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedidoUpdate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("cliente/pedidos")
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
	
	@PostMapping("{livroId}")
	public ResponseEntity cadastrar(Authentication authentication, @PathVariable Long livroId, @RequestBody @Valid DadosCadastroPedido dados, UriComponentsBuilder uriBuilder) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		Long id = user.getId();
		
		var dto = insert.criar(dados, id, livroId);
	    var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoItem>> listarItensPorCliente(
													   Authentication authentication,
	        										   @PageableDefault(size = 15, sort = "id") Pageable pageable) {

		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
	    Page<DadosDetalhamentoItem> itens = repositorioDeItem.listarItensDoCliente(id, pageable);
	    
	    return ResponseEntity.ok(itens);
	}

	@GetMapping("pagos")
	public ResponseEntity<Page<DadosDetalhamentoItensPagosPorCliente>> listarItensPagos(
	        										   Authentication authentication,
	        										   @PageableDefault(size = 15, sort = "dataPedido", direction = Sort.Direction.DESC) Pageable pageable) {
		
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
	    Page<DadosDetalhamentoItensPagosPorCliente> itens = repositorioDeItem.pedidosPagos(id, pageable);
	    
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