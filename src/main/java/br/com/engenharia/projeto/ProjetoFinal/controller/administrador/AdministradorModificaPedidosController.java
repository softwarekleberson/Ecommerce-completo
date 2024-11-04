package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dao.item.RepositorioDeItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCodigoPedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosExcluirPedidos;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagos;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceModificaPedido;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class AdministradorModificaPedidosController {

	@Autowired
	private ServiceModificaPedido service;
	
	@Autowired
	private RepositorioDeItem repositorioDeItem;
	
	@PutMapping("status/entrega/transporte")
	public ResponseEntity<Void> modificarStatusEntregaPedidoTransporte(@RequestBody @Valid DadosCodigoPedido dados) {
		service.modificarStatusTransportePedido(dados.codigo());
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("status/entrega/entregue")
	public ResponseEntity<Void> modificarStatusEntregaPedido(@RequestBody @Valid DadosCodigoPedido dados) {
		service.modificarStatusEntregaPedido(dados.codigo());
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("excluir/pedidos")
	public ResponseEntity<Void> excluirPedidosAutomaticamente(@RequestBody @Valid DadosExcluirPedidos dados) {
		service.excluirPedidosNaoFinalizados(dados.dias());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listar-pedidos")
	public ResponseEntity<Page<DadosDetalhamentoItensPagos>> listarTodosOsPedidosPagos(
	        										   @PageableDefault(size = 15, sort = "id") Pageable pageable) {

	    Page<DadosDetalhamentoItensPagos> itens = repositorioDeItem.listarTodosOsPedidos(pageable);
	    return ResponseEntity.ok(itens);
	}
}