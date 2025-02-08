package br.com.engenharia.projeto.ProjetoFinal.controller.endereco;

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

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosAtualizacaoCobrancas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosDetalhamentoCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeCobranca;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente/cobrancas")
@CrossOrigin(origins = "*")
public class CobrancaController {

	@Autowired
	private RepositorioDeCobranca repositorioDeCobranca;
	
	@PostMapping("{clienteId}")
	public ResponseEntity cadastrarCobranca(@PathVariable Long clienteId, @RequestBody @Valid DadosCadastroCobranca dados) {
		var cobranca = new Cobranca(dados);
		cobranca.setCliente(clienteId);
		repositorioDeCobranca.salvarNovaCobranca(cobranca);
		return ResponseEntity.ok(cobranca);
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCobranca>> listarEnderecosCobranca(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCobranca> cobrancas = repositorioDeCobranca.listarEnderecosCobrancaDoCliente(clienteId, pageable);
		return ResponseEntity.ok(cobrancas);
    }
	
	@PutMapping("{cobrancaId}")
	public  ResponseEntity atualizarCobranca(@PathVariable Long cobrancaId, @RequestBody @Valid DadosAtualizacaoCobrancas dados) {
		Cobranca updateCobranca = repositorioDeCobranca.alterar(cobrancaId, dados);
		return ResponseEntity.ok(updateCobranca);
	}
	
	@DeleteMapping("{idCobranca}")
	public ResponseEntity<Void> deletarEnderecoCobranca (@PathVariable Long idCobranca) {
		repositorioDeCobranca.excluir(idCobranca);
		return ResponseEntity.noContent().build();
	}	
}