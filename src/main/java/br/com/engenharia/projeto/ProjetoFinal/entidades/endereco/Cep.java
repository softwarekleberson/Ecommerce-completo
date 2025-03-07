package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Cep {

	private String cep;
	
	public Cep(String cep) {
		setCep(cep);
	}
	
	public void setCep(String cep) {
        String regexCep = "^\\d{5}-\\d{3}$";
		if(!cep.matches(regexCep)) {
			throw new ValidacaoCepException("Cep no formato irregular");
		}
		this.cep = cep.trim().toLowerCase();
	}
}