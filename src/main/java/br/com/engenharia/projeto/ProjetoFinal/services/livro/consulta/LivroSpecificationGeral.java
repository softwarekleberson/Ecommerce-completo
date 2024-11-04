package br.com.engenharia.projeto.ProjetoFinal.services.livro.consulta;

import org.springframework.data.jpa.domain.Specification;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;

public class LivroSpecificationGeral {

	 public static Specification<Livro> comTitulo(String termoDeBusca) {
	        return (root, query, cb) -> cb.like(cb.lower(root.get("titulo")), "%" + termoDeBusca.toLowerCase() + "%");
	    }

	    public static Specification<Livro> comIsbn(String termoDeBusca) {
	        return (root, query, cb) -> cb.like(cb.lower(root.get("isbn")), "%" + termoDeBusca.toLowerCase() + "%");
	    }

	    public static Specification<Livro> comEditora(String termoDeBusca) {
	        return (root, query, cb) -> cb.like(cb.lower(root.get("editora")), "%" + termoDeBusca.toLowerCase() + "%");
	    }

	    public static Specification<Livro> comAutorNome(String termoDeBusca) {
	        return (root, query, cb) -> cb.like(cb.lower(root.join("autores").get("nome")), "%" + termoDeBusca.toLowerCase() + "%");
	    }

	    public static Specification<Livro> comCategoriaNome(String termoDeBusca) {
	        return (root, query, cb) -> cb.like(cb.lower(root.join("categorias").get("nome")), "%" + termoDeBusca.toLowerCase() + "%");
	    }
}
