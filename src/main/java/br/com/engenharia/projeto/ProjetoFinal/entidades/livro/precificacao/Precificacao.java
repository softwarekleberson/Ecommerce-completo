package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao;

import java.math.BigDecimal;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Precificacao")
@Table(name = "precificacoes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Precificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "precificacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros;
	
	private BigDecimal precificacao;
	
	public Precificacao(DadosCadastroPrecificacao dados) {
		setPrecificacao(dados.precificacao());
	}
	
	public void setId(Long id) {
		this.id = id;
		System.out.println("id precificacao" + this.id);
	}
	
	public void setPrecificacao(BigDecimal precificacao) {
		if(precificacao == null || precificacao.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new ValidacaoException("A precificação deve ser um valor positivo maior que zero.");
		}
		this.precificacao = precificacao;
	}
}