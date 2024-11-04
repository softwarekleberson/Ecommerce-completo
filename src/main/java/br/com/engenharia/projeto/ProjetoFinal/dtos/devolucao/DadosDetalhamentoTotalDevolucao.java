package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.EsperandoDevolucaoOuRecebido;

public record DadosDetalhamentoTotalDevolucao(
		
		Long idDevolucao,
		String codigoDevolucao,
		String codigoPedido,
		LocalDate dataPedidoTroca,
		LocalDate dataConclusaoTroca,
		String emailAdm,
		EsperandoDevolucaoOuRecebido analisePedidoDevolucao,
		AnalisePedidoDevolucaoAceitoOuRecusa analiseDevolucao
		
		) {
	
	public DadosDetalhamentoTotalDevolucao(Devolucao dados) {
		this(dados.getId(), dados.getCodigoDevolucao(),
			 dados.getCodigoPedido(), dados.getDataPedidoTroca(),
			 dados.getDataConclusaoTroca(), dados.getAdministrador().getEmail().getEmail(),
			 dados.getEsperandoDevolucaoOuRecebido(),
			 dados.getAnalisePedido());
	}
}