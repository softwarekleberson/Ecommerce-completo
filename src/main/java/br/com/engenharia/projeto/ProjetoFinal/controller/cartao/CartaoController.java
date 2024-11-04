package br.com.engenharia.projeto.ProjetoFinal.controller.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosDetalhamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.RepositorioDeCartao;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ServiceCartao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cartoes")
@CrossOrigin(origins = "*")
public class CartaoController {

	@Autowired
	private ServiceCartao service;
	
	@Autowired
	private RepositorioDeCartao repositorioDeCartao;
		
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCartao dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
	    var uri = uriBuilder.path("/cartao/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("{cartaoId}")
	public ResponseEntity atualizar(@PathVariable Long cartaoId, @RequestBody @Valid DadosAtualizacaoCartao dados) {
		DadosDetalhamentoCartao detalhamentoCartao = service.atualizar(dados, cartaoId);
        return ResponseEntity.ok(detalhamentoCartao);
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCartao>> listarPorCliente(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCartao> cartoes = repositorioDeCartao.listarCartaosDoCliente(clienteId, pageable);
		return ResponseEntity.ok(cartoes);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id) {
		repositorioDeCartao.deletar(id);
		return ResponseEntity.noContent().build();
	}	
}