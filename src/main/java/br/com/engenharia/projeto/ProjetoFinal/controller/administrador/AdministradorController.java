package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAdministrador;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class AdministradorController {

	@Autowired
	private ServiceAdministrador serviceAdministrador;

	@PostMapping
	public ResponseEntity cadastrarAdministrador(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
		var dto = serviceAdministrador.criarAdministrador(dados);
	    var uri = uriBuilder.path("/administradores/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}