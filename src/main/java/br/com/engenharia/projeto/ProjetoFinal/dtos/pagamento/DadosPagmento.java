package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;

public record DadosPagmento(
		
		String id,
		StatusCompra statusCompra
		
		) {

	public DadosPagmento(Pagamento dados) {
		this(dados.getId(), dados.getStatusCompra());
	}
}
