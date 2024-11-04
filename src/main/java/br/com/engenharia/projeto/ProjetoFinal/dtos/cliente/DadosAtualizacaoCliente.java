package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Genero;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.TipoTelefone;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
    
	@NotNull
    Long idCliente,
    Genero genero,
    boolean ativo,
    String nome,
    LocalDate nascimento,
	String email,
	String ddd,
	String telefone,
	TipoTelefone tipo
	
) {
	
}
