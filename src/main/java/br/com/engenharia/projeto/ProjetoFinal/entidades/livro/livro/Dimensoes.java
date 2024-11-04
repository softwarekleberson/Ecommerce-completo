package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroDimensao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensoes {

	private double altura;
	private double largura;
	private double peso;
	private double profundidade;
	
	public Dimensoes(DadosCadastroDimensao dados) {
		setAltura(dados.altura());
		setLargura(dados.largura());
		setPeso(dados.peso());
		setProfundidade(dados.profundidade());
	}
	
	public void setAltura(double altura) {
		if(altura <= 0) {
			throw new ValidacaoException("Altura deve ser superior a 0");
		}
		this.altura = altura;
	}
	
	public void setLargura(double largura) {
		if(largura <= 0) {
			throw new ValidacaoException("Largura deve ser superior a 0");
		}
		this.largura = largura;
	}
	
	public void setPeso(double peso) {
		if(peso <= 0) {
			throw new ValidacaoException("Peso deve ser superior a 0");
		}
		this.peso = peso;
	}
	
	public void setProfundidade(double profundidade) {
		if(profundidade <= 0) {
			throw new ValidacaoException("Profudidade deve ser superior a 0");
		}
		this.profundidade = profundidade;
	}
}
