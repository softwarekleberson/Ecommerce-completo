package br.com.engenharia.projeto.ProjetoFinal.controller.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosDetalhamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.RepositorioDeCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ServiceCartao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente/cartoes")
@CrossOrigin(origins = "*")
public class CartaoController {

	@Autowired
	private ServiceCartao service;
	
	@Autowired
	private RepositorioDeCartao repositorioDeCartao;
		
	@PostMapping
	public ResponseEntity cadastrar(Authentication authentication, @RequestBody @Valid DadosCadastroCartao dados, UriComponentsBuilder uriBuilder) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		Long id = user.getId();
		
		var dto = service.criar(id, dados);
	    var uri = uriBuilder.path("/cartao/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoCartao>> listarPorCliente(Authentication authentication, Pageable pageable){
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		Page<DadosDetalhamentoCartao> cartoes = repositorioDeCartao.listarCartaosDoCliente(id, pageable);
		return ResponseEntity.ok(cartoes);
    }
	
	@PutMapping("/{cartaoId}")
	public ResponseEntity atualizar(Authentication authentication, @PathVariable Long cartaoId, @RequestBody @Valid DadosAtualizacaoCartao dados) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		Long id = user.getId();
		
		DadosDetalhamentoCartao detalhamentoCartao = service.atualizar(dados, cartaoId, id);
        return ResponseEntity.ok(detalhamentoCartao);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (Authentication authentication, @PathVariable Long id) {
		repositorioDeCartao.deletar(id);
		return ResponseEntity.noContent().build();
	}	
}