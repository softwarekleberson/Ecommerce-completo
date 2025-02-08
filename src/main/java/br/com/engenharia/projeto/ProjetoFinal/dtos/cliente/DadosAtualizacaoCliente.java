package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Genero;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.TipoTelefone;

public record DadosAtualizacaoCliente(
    
    Genero genero,
    boolean ativo,
    String nome,
    LocalDate nascimento,
	String ddd,
	String telefone,
	TipoTelefone tipo
	
) {
	
}
