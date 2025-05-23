package br.com.engenharia.projeto.ProjetoFinal.controller.pagamento;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.services.pagamento.ServicePagamento;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

	@Autowired
	private ServicePagamento insert;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(Authentication authentication, @RequestBody @Valid DadosCadastroPagamento dados, UriComponentsBuilder uriBuilder) {
		UserEntity user = (UserEntity) authentication.getPrincipal(); 
		Long id = user.getId();
		
		var dto = insert.validarDadosDoPagamento(dados, id);
	    if (dto.statusCompra() == StatusCompra.REPROVADO) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Pagamento reprovado."));
	    }

	    var uri = uriBuilder.path("/pagamento/{id}").buildAndExpand(dto.id()).toUri();
	    return ResponseEntity.created(uri).body(dto);
	}
}