package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;

public record DetalharCliente(
		
		Long id, String nome, LocalDate nascimento, Email email,
		String ddd, String telefone
		
		) {

	public DetalharCliente(Cliente cliente) {
		this(cliente.getId() ,cliente.getNome(), cliente.getNascimento(),
			 cliente.getEmail(), cliente.getTelefone().getDdd(),
		     cliente.getTelefone().getTelefone());
	}
}
