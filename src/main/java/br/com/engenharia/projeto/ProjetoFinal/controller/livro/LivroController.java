package br.com.engenharia.projeto.ProjetoFinal.controller.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroStatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivroCompleto;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.LivroConsultaDto;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.LivroConsultaGeralDto;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceAtivarInativarLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceGetLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceInsertLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceUpdateLivro;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.consulta.LivroConsultaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livro")
@CrossOrigin(origins = "*")
public class LivroController {

	@Autowired
	private ServiceInsertLivro insertLivro;
	
	@Autowired
	private ServiceUpdateLivro updateLivro;
	
	@Autowired
	private ServiceGetLivro listLivros;
	
	@Autowired
	private ServiceAtivarInativarLivro deleteLivro;
	
	@Autowired
	private LivroConsultaService livroConsultaService;
		
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
		var dto = insertLivro.criar(dados);
		var uri = uriBuilder.path("/livro/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("consulta/pesquisa")
	public Page<DadosDetalhamentoLivroCompleto> buscarLivrosPorTermo(
	    @RequestBody LivroConsultaGeralDto livroConsultaGeralDto,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size) {

	    return livroConsultaService.buscarLivrosPorTermoGeral(livroConsultaGeralDto, page, size);
	}

	@PostMapping("/consulta/parametros")
	public Page<DadosDetalhamentoLivroCompleto> buscarLivros(
	    @RequestBody LivroConsultaDto livroConsultaDTO,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size) {

	    return livroConsultaService.buscarLivros(livroConsultaDTO, page, size);
	}

	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoLivro>> listarLivrosAtivos(@PageableDefault(size = 15) Pageable paginacao){
		Page<DadosDetalhamentoLivro> page = listLivros.listarLivros(paginacao);
        return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalharLivro(@PathVariable Long id) {
		DadosDetalhamentoLivroCompleto livro = listLivros.listarLivroExpecifico(id);
		System.out.println(livro);
		return ResponseEntity.ok(livro);
	}
	
	@PutMapping
	public ResponseEntity atualizarLivro(@RequestBody @Valid DadosAtualizarLivro dados) {
		DadosDetalhamentoLivro livro = updateLivro.atualizarLivro(dados);
		return ResponseEntity.ok(livro);
	}
	
	@PutMapping("ativar")
	public ResponseEntity ativarLivro(@RequestBody @Valid DadosCadastroStatusLivro dados) {
		deleteLivro.ativarLogica(dados);		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("inativar")
	public ResponseEntity inativarLivroManualmente(@RequestBody @Valid DadosCadastroStatusLivro dados) {
		deleteLivro.delecaoLogica(dados);		
		return ResponseEntity.noContent().build();
	}
}