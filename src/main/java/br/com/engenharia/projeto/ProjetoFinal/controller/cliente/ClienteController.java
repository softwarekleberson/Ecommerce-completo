package br.com.engenharia.projeto.ProjetoFinal.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosDetalhamentoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.RepositorioDeCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ServiceClienteUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	private ServiceClienteUpdate serviceClienteUpdate;
	
	@Autowired
	private RepositorioDeCliente repositorioDeCliente;
	
	@Autowired
	private RepositorioDeCupom repositorioDeCupom;
	
	@GetMapping("/cupons")
	public ResponseEntity<Page<DadosDetalhamentoCupom>> listarCupomPorCliente(
	        Authentication authentication,
	        @PageableDefault(sort = "valor", direction = Sort.Direction.DESC) Pageable pageable) {
	    
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		Page<DadosDetalhamentoCupom> cupons = repositorioDeCupom.listarCuponsDosClientes(id, pageable);
	    return ResponseEntity.ok(cupons);
	}

	@PutMapping("/atualizar")
	public  ResponseEntity atualizarCliente(Authentication authentication, @RequestBody @Valid DadosAtualizacaoCliente dados) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); // Pega o usuário autenticado
		Long id = user.getId();
		
		DadosDetalhamentoCliente updateCliente = serviceClienteUpdate.atualizarCliente(dados, id);
		return ResponseEntity.ok(updateCliente);
	}
	
	@PutMapping("/senha")
	public  ResponseEntity atualizarSenha(Authentication authentication, @RequestBody @Valid DadosAtualizacaoSenha dados) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		DadosDetalhamentoCliente detalharCliente = serviceClienteUpdate.atualizarSenha(dados, id);
		return ResponseEntity.ok(detalharCliente);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar (Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		repositorioDeCliente.deletar(id);
		return ResponseEntity.noContent().build();
	}	
}