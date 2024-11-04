package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;

public interface IstrategyValidaCartao {

	public void processar(DadosCadastroCartao dados);
}
