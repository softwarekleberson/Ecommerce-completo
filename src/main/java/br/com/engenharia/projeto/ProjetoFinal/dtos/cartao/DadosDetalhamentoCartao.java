package br.com.engenharia.projeto.ProjetoFinal.dtos.cartao;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Bandeira;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;

public record DadosDetalhamentoCartao(
		
		Long idCliente,
		Long id,
		String nomeImpresso,
		String codigo,
		String numeroCartao,
		Bandeira bandeira,
		boolean principal
									  ) {

	public DadosDetalhamentoCartao(Cartao dados) {
		this(dados.getCliente().getId(), dados.getId() ,dados.getNomeImpresso(), dados.getCodigo(),
			 dados.getNumeroCartao(),dados.getBandeira(), dados.isPrincipal());
	}
}
