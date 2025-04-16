package br.com.engenharia.projeto.ProjetoFinal.controller.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosAtualizacaoEntregas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosDetalhamentoEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

	@Autowired
	private RepositorioDeEntrega repositorioDeEntrega;
	
	@PostMapping
	public ResponseEntity cadastrarEntrega(Authentication authentication, @RequestBody @Valid DadosCadastroEntrega dados) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 		
		Long id = user.getId();

		var entrega = new Entrega(dados);
		entrega.setCliente(id);
		repositorioDeEntrega.salvarNovoEntrega(entrega);
		return ResponseEntity.ok(entrega);
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<DadosDetalhamentoEntrega> obterEntregaPorId(
	        Authentication authentication, 
	        @PathVariable Long entregaId) {
	    
	    UserEntity user = (UserEntity) authentication.getPrincipal();

	    Entrega entrega = repositorioDeEntrega.entregaPorId(entregaId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada"));

	    DadosDetalhamentoEntrega dados = new DadosDetalhamentoEntrega(entrega);
	    
	    return ResponseEntity.ok(dados);
	}

	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoEntrega>> listarEnderecosEntrega(Authentication authentication, Pageable pageable){
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		Page<DadosDetalhamentoEntrega> entregas = repositorioDeEntrega.listarEntregasDoCliente(id, pageable);
		return ResponseEntity.ok(entregas);
    }
	
	@PutMapping("/{entregaId}")
	public  ResponseEntity atualizarEntrega(Authentication authentication, @PathVariable Long entregaId, @RequestBody @Valid DadosAtualizacaoEntregas dados) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		Entrega updateEntrega = repositorioDeEntrega.alterar(entregaId, dados);
		return ResponseEntity.ok(updateEntrega);
	}
	
	@DeleteMapping("/{idEntrega}")
	public ResponseEntity<Void> deletarEnderecoEntrega (Authentication authentication, @PathVariable Long idEntrega) {
		repositorioDeEntrega.excluir(idEntrega);
		return ResponseEntity.noContent().build();
	}
}