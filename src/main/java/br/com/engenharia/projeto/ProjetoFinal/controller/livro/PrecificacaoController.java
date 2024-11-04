package br.com.engenharia.projeto.ProjetoFinal.controller.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.services.precificacao.ServicePrecificacao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("precificacao")
@CrossOrigin(origins = "*")
public class PrecificacaoController {

	@Autowired
	private ServicePrecificacao service;
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPrecificacao dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
		var uri = uriBuilder.path("/precificacao/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
