package br.com.engenharia.projeto.ProjetoFinal.controller.endereco;

import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosAtualizacaoCobrancas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosDetalhamentoCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente/cobrancas")
@CrossOrigin(origins = "*")
public class CobrancaController {

	@Autowired
	private RepositorioDeCobranca repositorioDeCobranca;
	
	@PostMapping
	public ResponseEntity cadastrarCobranca(Authentication authentication, @RequestBody @Valid DadosCadastroCobranca dados, UriComponentsBuilder uriBuilder) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		var cobranca = new Cobranca(dados);
		cobranca.setCliente(id);

		repositorioDeCobranca.salvarNovaCobranca(cobranca);
		return ResponseEntity.ok(cobranca);
	}
	
	@GetMapping("/{cobrancaId}")
	public ResponseEntity<Cobranca> obterCobrancaPorId(
	        Authentication authentication, 
	        @PathVariable Long cobrancaId) {
	    
	    UserEntity user = (UserEntity) authentication.getPrincipal();

	    Cobranca cobranca = repositorioDeCobranca.cobrancaPorId(cobrancaId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cobrança não encontrada"));

	    return ResponseEntity.ok(cobranca);
	}

	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoCobranca>> listarEnderecosCobranca(Authentication authentication, Pageable pageable){
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		Page<DadosDetalhamentoCobranca> cobrancas = repositorioDeCobranca.listarEnderecosCobrancaDoCliente(id, pageable);
		return ResponseEntity.ok(cobrancas);
    }
	
	@PutMapping("/{cobrancaId}")
	public  ResponseEntity atualizarCobranca(Authentication authentication, @PathVariable Long cobrancaId, @RequestBody @Valid DadosAtualizacaoCobrancas dados) {
		Cobranca updateCobranca = repositorioDeCobranca.alterar(cobrancaId, dados);
		return ResponseEntity.ok(updateCobranca);
	}
	
	@DeleteMapping("/{idCobranca}")
	public ResponseEntity<Void> deletarEnderecoCobranca (Authentication authentication, @PathVariable Long idCobranca) {
		repositorioDeCobranca.excluir(idCobranca);
		return ResponseEntity.noContent().build();
	}	
}