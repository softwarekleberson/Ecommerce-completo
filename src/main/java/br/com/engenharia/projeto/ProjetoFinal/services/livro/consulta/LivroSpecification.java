package br.com.engenharia.projeto.ProjetoFinal.services.livro.consulta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import jakarta.persistence.criteria.Join;

public class LivroSpecification {

    public static Specification<Livro> comPrecoMenorQue(BigDecimal precoMenor) {
        return (root, query, criteriaBuilder) -> {
            if (precoMenor == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("preco"), precoMenor);
        };
    }

    public static Specification<Livro> comPrecoMaiorQue(BigDecimal precoMaior) {
        return (root, query, criteriaBuilder) -> {
            if (precoMaior == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("preco"), precoMaior);
        };
    }

    public static Specification<Livro> comDataMenorQue(LocalDate dataMenor) {
        return (root, query, criteriaBuilder) -> {
            if (dataMenor == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("data"), dataMenor);
        };
    }

    public static Specification<Livro> comDataMaiorQue(LocalDate dataMaior) {
        return (root, query, criteriaBuilder) -> {
            if (dataMaior == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("data"), dataMaior);
        };
    }

    public static Specification<Livro> comTitulo(String titulo) {
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Livro> comIsbn(String isbn) {
        return (root, query, criteriaBuilder) -> {
            if (isbn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isbn"), isbn.trim().toLowerCase());
        };
    }

    public static Specification<Livro> comMaisPaginasQue(Integer paginas) {
        return (root, query, criteriaBuilder) -> {
            if (paginas == null || paginas <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("paginas"), paginas);
        };
    }

    public static Specification<Livro> comEditora(String editora) {
        return (root, query, criteriaBuilder) -> {
            if (editora == null || editora.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("editora")), "%" + editora.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Livro> comEdicao(String edicao) {
        return (root, query, criteriaBuilder) -> {
            if (edicao == null || edicao.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("edicao")), "%" + edicao.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Livro> comAutores(List<Long> autorIds) {
        return (root, query, criteriaBuilder) -> {
            if (autorIds == null || autorIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Livro, Autor> autorJoin = root.join("autores");
            return autorJoin.get("id").in(autorIds);
        };
    }

    public static Specification<Livro> comCategorias(List<Long> categoriaIds) {
        return (root, query, criteriaBuilder) -> {
            if (categoriaIds == null || categoriaIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Livro, Categoria> categoriaJoin = root.join("categorias");
            return categoriaJoin.get("id").in(categoriaIds);
        };
    }

    public static Specification<Livro> comAlturaMaiorQue(Double alturaMaior) {
        return (root, query, criteriaBuilder) -> {
            if (alturaMaior == null || alturaMaior <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("dimensoes").get("altura"), alturaMaior);
        };
    }

    public static Specification<Livro> comAlturaMenorQue(Double alturaMenor) {
        return (root, query, criteriaBuilder) -> {
            if (alturaMenor == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("dimensoes").get("altura"), alturaMenor);
        };
    }

    public static Specification<Livro> comLarguraMaiorQue(Double larguraMaior) {
        return (root, query, criteriaBuilder) -> {
            if (larguraMaior == null || larguraMaior <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("dimensoes").get("largura"), larguraMaior);
        };
    }

    public static Specification<Livro> comLarguraMenorQue(Double larguraMenor) {
        return (root, query, criteriaBuilder) -> {
            if (larguraMenor == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("dimensoes").get("largura"), larguraMenor);
        };
    }

    public static Specification<Livro> comPesoMaiorQue(Double pesoMaior) {
        return (root, query, criteriaBuilder) -> {
            if (pesoMaior == null || pesoMaior <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("dimensoes").get("peso"), pesoMaior);
        };
    }

    public static Specification<Livro> comPesoMenorQue(Double pesoMenor) {
        return (root, query, criteriaBuilder) -> {
            if (pesoMenor == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("dimensoes").get("peso"), pesoMenor);
        };
    }

    public static Specification<Livro> comProfundidadeMaiorQue(Double profundidadeMaior) {
        return (root, query, criteriaBuilder) -> {
            if (profundidadeMaior == null || profundidadeMaior <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("dimensoes").get("profundidade"), profundidadeMaior);
        };
    }

    public static Specification<Livro> comProfundidadeMenorQue(Double profundidadeMenor) {
        return (root, query, criteriaBuilder) -> {
            if (profundidadeMenor == null || profundidadeMenor <= 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("dimensoes").get("profundidade"), profundidadeMenor);
        };
    }

	public static Specification<Livro> comSinopse(String termoDeBusca) {
		 return (root, query, criteriaBuilder) ->
	        criteriaBuilder.like(root.get("sinopse"), "%" + termoDeBusca + "%");
	}

}
