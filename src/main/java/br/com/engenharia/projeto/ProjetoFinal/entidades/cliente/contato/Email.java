package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Email {

	private String email;
	
	public Email(String email) {
		setEmail(email);
	}
	
	public void setEmail(String email) { 
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		if (!matcher.matches()) {
	        throw new ValidacaoException("Formato de email inválido");
	    }
		 
		this.email = email.trim().toLowerCase();
	}
}