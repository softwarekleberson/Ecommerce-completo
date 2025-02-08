package br.com.engenharia.projeto.ProjetoFinal.controller.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroStatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceAtivarInativarLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceInsertLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceUpdateLivro;
import jakarta.validation.Valid;

@RestController
@RequestMapping("admin/livro")
@CrossOrigin(origins = "*")
public class LivroAdmController {

	@Autowired
	private ServiceInsertLivro insertLivro;
	
	@Autowired
	private ServiceUpdateLivro updateLivro;
	
	@Autowired
	private ServiceAtivarInativarLivro deleteLivro;
			
	@PostMapping
	public ResponseEntity cadastrarLivro(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
		var dto = insertLivro.criar(dados);
		var uri = uriBuilder.path("/livro/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping
	public ResponseEntity atualizarLivro(@RequestBody @Valid DadosAtualizarLivro dados) {
		DadosDetalhamentoLivro livro = updateLivro.atualizarLivro(dados);
		return ResponseEntity.ok(livro);
	}
	
	@PutMapping("ativar")
	public ResponseEntity ativarManualmenteLivro(@RequestBody @Valid DadosCadastroStatusLivro dados) {
		deleteLivro.ativarLogica(dados);		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("inativar")
	public ResponseEntity inativarManualmenteLivro(@RequestBody @Valid DadosCadastroStatusLivro dados) {
		deleteLivro.delecaoLogica(dados);		
		return ResponseEntity.noContent().build();
	}
}