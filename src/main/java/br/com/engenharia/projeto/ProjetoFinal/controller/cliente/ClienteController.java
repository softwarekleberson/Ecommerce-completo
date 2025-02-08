package br.com.engenharia.projeto.ProjetoFinal.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosDetalhamentoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
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
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		var page = repositorioDeCliente.pegaTodosClientes(paginacao);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	public  ResponseEntity atualizarCliente(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		DadosDetalhamentoCliente updateCliente = serviceClienteUpdate.atualizarCliente(dados);
		return ResponseEntity.ok(updateCliente);
	}
	
	@PutMapping("/senha")
	public  ResponseEntity atualizarSenha(@RequestBody @Valid DadosAtualizacaoSenha dados) {
		DadosDetalhamentoCliente detalharCliente = serviceClienteUpdate.atualizarSenha(dados);
		return ResponseEntity.ok(detalharCliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id) {
		repositorioDeCliente.deletar(id);
		return ResponseEntity.noContent().build();
	}	
}