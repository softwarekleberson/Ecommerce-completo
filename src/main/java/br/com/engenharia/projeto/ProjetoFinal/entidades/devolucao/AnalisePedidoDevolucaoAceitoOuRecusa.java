package br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao;

public enum AnalisePedidoDevolucaoAceitoOuRecusa {

	EM_ANALISE, TROCA_ACEITA, TROCA_RECUSADA;
	
	public AnalisePedidoDevolucaoAceitoOuRecusa avaliar(AnalisePedidoDevolucaoAceitoOuRecusa status) {
		if(status != EM_ANALISE) {
			throw new AvaliacaoRealizadaException("Está avaliação foi realizada anteriormente");
		}
		return status;
	}
}
