package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.statusLivro;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroStatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "statuslivro")
@Entity(name = "StatusLivro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusLivro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String justificativa;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "livro_id")
	private Livro livro;
	
	public StatusLivro(DadosCadastroStatusLivro dados) {
		setJustificativa(dados.justificativa());
		setCategoria(dados.categoria());
	}
	
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}