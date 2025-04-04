package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.TipoTelefone;

public record DadosDetalhamentoCliente(
		Long id, String nome, LocalDate nascimento,
		String ddd, String telefone, TipoTelefone tipo
		
		)

{

	public DadosDetalhamentoCliente(Cliente cliente) {
		this(cliente.getId() ,cliente.getNome(), cliente.getNascimento(),
			 cliente.getTelefone().getDdd(),
			 cliente.getTelefone().getTelefone(), cliente.getTelefone().getTipoTelefone());
	}

}
