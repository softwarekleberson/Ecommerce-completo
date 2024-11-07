package br.com.engenharia.projeto.ProjetoFinal.controller.pagamento;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.services.pagamento.ServicePagamento;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

	@Autowired
	private ServicePagamento insert;
	
	@PostMapping("{clienteId}")
	public ResponseEntity<?> cadastrar(@PathVariable Long clienteId, @RequestBody @Valid DadosCadastroPagamento dados, UriComponentsBuilder uriBuilder) {
	    var dto = insert.validarDadosDoPagamento(dados, clienteId);
	    
	    if (dto.statusCompra() == StatusCompra.REPROVADO) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Pagamento reprovado."));
	    }

	    var uri = uriBuilder.path("/pagamento/{id}").buildAndExpand(dto.id()).toUri();
	    return ResponseEntity.created(uri).body(dto);
	}

}