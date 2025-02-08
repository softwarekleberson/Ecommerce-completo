package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;

public record DadosDetalhamentoCliente(
		Long id, String nome, LocalDate nascimento,
		String ddd, String telefone
		
		)

{

	public DadosDetalhamentoCliente(Cliente cliente) {
		this(cliente.getId() ,cliente.getNome(), cliente.getNascimento(),
			 cliente.getTelefone().getDdd(),
			 cliente.getTelefone().getTelefone());
	}

}
