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
public class Pais {

	private String pais;

	public Pais(DadosCadastroEntrega dados) {
		setPais(dados.paisEntrega());
	}
	
	public Pais(DadosCadastroCobranca dados) {
		setPais(dados.paisCobranca());
	}
	
	public void setPais(String pais) {
		if(pais == null || pais.trim().length() == 0) {
			throw new ValidacaoException("Pais não deve ser nulo");
		}
		this.pais = pais.trim().toLowerCase();
	}
}