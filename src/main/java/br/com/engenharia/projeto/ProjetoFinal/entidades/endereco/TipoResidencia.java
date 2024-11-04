package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TipoResidencia {

	private String tipoResidencia;

	public TipoResidencia(DadosCadastroEntrega dados) {
		setTipoResidencia(dados.tipoResidenciaEntrega());
	}
	
	public TipoResidencia(DadosCadastroCobranca dados) {
		setTipoResidencia(dados.tipoResidenciaCobranca());
	}

	public void setTipoResidencia(String tipoResidencia) {
		if(tipoResidencia == null || tipoResidencia.trim().length() == 0) {
			throw new ValidacaoException("Tipo residencia não deve ser nulo");
		}
		this.tipoResidencia = tipoResidencia.trim().toLowerCase();
	}
}