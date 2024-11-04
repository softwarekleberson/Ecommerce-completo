package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Estado{

	private String estado;
	
	@Embedded
	private Pais pais;
	
	public Estado(DadosCadastroCobranca dados) {
		setEstado(dados.estadoCobranca());
		setPais(dados.paisCobranca());
	}

	public Estado(DadosCadastroEntrega dados) {
		setEstado(dados.estadoEntrega());
		setPais(dados.paisEntrega());
	}	
	
	public void setEstado(String estado) {
		if(estado == null || estado.trim().length() == 0) {
			throw new ValidacaoException("Estado não deve ser nulo");
		}
		this.estado = estado.trim().toLowerCase();
	}
	
	public void setPais(String pais) {
		this.pais = new Pais(pais);
	}
}