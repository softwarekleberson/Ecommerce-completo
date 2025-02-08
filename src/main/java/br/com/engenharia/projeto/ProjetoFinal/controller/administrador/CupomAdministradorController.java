package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.ValidarCupom;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceGeraCupomPromocioal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("admin/cupons")
@CrossOrigin(origins = "*")
public class CupomAdministradorController {

	@Autowired
	private ServiceGeraCupomPromocioal serviceGeraCupomPromocioal;

	@PostMapping("/gerar")
	public ResponseEntity cadastrarCupomPromocional(@RequestBody @Valid DadosCadastroCupom dados, UriComponentsBuilder uriBuilder) {
		var dto = serviceGeraCupomPromocioal.criarCupom(dados);
	    var uri = uriBuilder.path("/cupom/{id}").buildAndExpand(dto.idCliente()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/validar")
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
}