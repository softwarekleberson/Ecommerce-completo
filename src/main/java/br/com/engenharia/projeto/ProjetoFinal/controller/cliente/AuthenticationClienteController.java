package br.com.engenharia.projeto.ProjetoFinal.controller.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.services.cliente.ServiceCliente;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.AuthResponse;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.AuthenticationService;
import br.com.engenharia.projeto.ProjetoFinal.services.seguranca.LoginRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationClienteController {

	private final ServiceCliente service;
    private final AuthenticationService authenticationService;
    
    public AuthenticationClienteController(ServiceCliente service, AuthenticationService authenticationService) {
		this.service = service;
		this.authenticationService = authenticationService;
	}

    @PostMapping("/login/cliente")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authenticationService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/criar/cliente")
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
	    var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
