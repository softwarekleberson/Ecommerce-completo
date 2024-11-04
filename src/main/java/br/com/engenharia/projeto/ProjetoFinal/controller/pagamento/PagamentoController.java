package br.com.engenharia.projeto.ProjetoFinal.controller.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.services.pagamento.ServicePagamento;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

	@Autowired
	private ServicePagamento insert;
	
	@PostMapping("{clienteId}")
	public ResponseEntity<Void> cadastrar(@PathVariable Long clienteId, @RequestBody @Valid DadosCadastroPagamento dados) {
	    insert.validarDadosDoPagamento(dados, clienteId);
	    return ResponseEntity.ok().build();
	}
}