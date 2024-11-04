package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroAutor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Autor")
@Table(name = "autores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
	    private List<Livro> livros = new ArrayList<>();

	    private String autor;

	    public Autor(DadosCadastroAutor dados) {
	        setAutor(dados.nome());
	    }

	    public void setAutor(String autor) {
	        if (autor == null || autor.trim().isEmpty()) {
	            throw new ValidacaoException("Nome do autor não deve ser nulo ou vazio");
	        }
	        this.autor = autor.trim().toLowerCase();
	    }
}