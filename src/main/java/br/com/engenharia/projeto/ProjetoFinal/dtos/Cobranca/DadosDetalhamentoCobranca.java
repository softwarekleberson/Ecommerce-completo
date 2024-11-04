package br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;

public record DadosDetalhamentoCobranca(
		
		Long id, String logradouro, String numero,
		String bairro, String cep, String observacao,
		String tipoLogradouto,
		String tipoResidencia, String cidade,
		String estado, String pais, boolean principal,
		String receptor
		
		) {

	public DadosDetalhamentoCobranca(Cobranca cobranca) {
		this(cobranca.getId(), cobranca.getLogradouro(),
				cobranca.getNumero(),
				cobranca.getBairro(),
				cobranca.getCep().getCep(),
				cobranca.getObservacao(),
				cobranca.getTipoLogradouro().getTipoLogradouro(),
				cobranca.getTipoResidencia().getTipoResidencia(),
				cobranca.getCidade().getCidade(),
				cobranca.getCidade().getEstado().getEstado(), 
				cobranca.getCidade().getEstado().getPais().getPais(),
				cobranca.isPrincipal(), cobranca.getReceptor());
	}
}