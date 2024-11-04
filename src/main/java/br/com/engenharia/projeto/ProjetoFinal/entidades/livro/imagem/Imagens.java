package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroImagem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
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

@Getter
@Entity(name = "Imagens")
@Table(name = "imagens")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Imagens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "livro_id")
	private Livro livro;
	
	private String url;
	
	public Imagens(DadosCadastroImagem dados) {
		setUrl(dados.url());
	}
	
	public void setLivro(Livro livro) {
        this.livro = livro;
    }
	
	public void setUrl(String url) {
	    String regex = "^(https?|ftp):\\/\\/(\\-\\.)?([^\\s\\/\\?\\.#]+\\.?)+(\\/[^\\s]*)?$";
		if(!url.matches(regex)) {
			throw new ValidacaoException("Url invalida");
		}
		this.url = url;
	}
}
