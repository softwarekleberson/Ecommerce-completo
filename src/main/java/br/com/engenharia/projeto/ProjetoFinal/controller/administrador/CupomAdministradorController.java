package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.ValidarCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.RepositorioDeCupom;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceGeraCupomPromocioal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class CupomAdministradorController {

	@Autowired
	private ServiceGeraCupomPromocioal serviceGeraCupomPromocioal;
	
	@Autowired
	private RepositorioDeCupom repositorioDeCupom;

	@PostMapping("/cupons")
	public ResponseEntity cadastrarCupomPromocional(@RequestBody @Valid DadosCadastroCupom dados, UriComponentsBuilder uriBuilder) {
		var dto = serviceGeraCupomPromocioal.criarCupom(dados);
	    var uri = uriBuilder.path("/cupom/{id}").buildAndExpand(dto.idCliente()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/validar/cupom")
	public ResponseEntity<String> validarCupom(@RequestBody @Valid ValidarCupom dados, UriComponentsBuilder uriBuilder) {
		var cupomValido = serviceGeraCupomPromocioal.validarCupomUsuario(dados);
		if(cupomValido) {
			return ResponseEntity.ok().body("Cupom válido");
		}
		
		else {
			return ResponseEntity.status
				   (HttpStatus.BAD_REQUEST)
				   .body("Cupom inválido");
		}
	}
	
	@GetMapping("/cupons/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCupom>> listarCupomPorCliente(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCupom> cupons = repositorioDeCupom.listarCuponsDosClientes(clienteId, pageable);
		return ResponseEntity.ok(cupons);
    }
}