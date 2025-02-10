package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TipoLogradouro {

	private String tipoLogradouro;

	public TipoLogradouro(DadosCadastroEntrega dados) {
		setTipoLogradouro(dados.tipoLogradouroEntrega());
	}
	
	public TipoLogradouro(DadosCadastroCobranca dados) {
		setTipoLogradouro(dados.tipoLogradouroCobranca());
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		if(tipoLogradouro == null || tipoLogradouro.trim().length() == 0) {
			throw new ValidacaoLogradouroException("Tipo logradouro não deve ser nulo");
		}
		this.tipoLogradouro = tipoLogradouro.trim().toLowerCase();
	}
}