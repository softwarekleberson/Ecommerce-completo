package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;

public interface IStrategySenhaAtualizadaCliente {

	void processar(DadosAtualizacaoSenha dados); 
}
