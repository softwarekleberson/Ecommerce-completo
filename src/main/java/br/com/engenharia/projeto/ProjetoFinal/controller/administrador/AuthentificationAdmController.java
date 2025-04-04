package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.AuthResponse;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.AuthenticationService;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.LoginRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthentificationAdmController {

	private final AuthenticationService authenticationService;
	private final ServiceAdministrador serviceAdministrador;
	
	public AuthentificationAdmController(AuthenticationService authenticationService,
			ServiceAdministrador serviceAdministrador) {
		this.authenticationService = authenticationService;
		this.serviceAdministrador = serviceAdministrador;
	}
	
	@PostMapping("/login/adm")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		String token = authenticationService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
	
	@PostMapping("/criar/adm")
	public ResponseEntity cadastrarAdministrador(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
		var dto = serviceAdministrador.criarAdministrador(dados);
	    var uri = uriBuilder.path("/administradores/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}