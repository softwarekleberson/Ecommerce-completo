package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroCategoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
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
@Entity(name = "Categoria")
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @ManyToMany(mappedBy = "categorias")
	 private List<Livro> livros = new ArrayList<>(); 

	 private String categoria;

	 public Categoria(DadosCadastroCategoria dados) {
	     setCategoria(dados.nome());
	 }
	 
	 public void setCategoria(String categoria) {
	     if (categoria == null || categoria.trim().isEmpty()) {
	       throw new ValidacaoException("Nome da categoria não deve ser nulo ou vazio");
	     }
	     this.categoria = categoria.trim().toLowerCase();
	 }
}