package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;

public interface IStrategyCliente {

	public void processar(Cliente dominio);
}
