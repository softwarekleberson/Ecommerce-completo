package br.com.engenharia.projeto.ProjetoFinal.controller.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.services.devolucao.ServiceGerarPedidoDevolucao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("devolucoes")
@CrossOrigin(origins = "*")
public class DevolucaoController {

	@Autowired
	private ServiceGerarPedidoDevolucao service;
	
	@PostMapping("{idCliente}")
	public ResponseEntity cadastrarPedidoDevolucao(@PathVariable Long idCliente, @RequestBody @Valid DadosCadastroDevolucao dados, UriComponentsBuilder uriBuilder) {
		var dto = service.pedidoDevolucao(dados, idCliente);
	    var uri = uriBuilder.path("/devolucoes/{id}").buildAndExpand(dto.codigoDevolucao()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}