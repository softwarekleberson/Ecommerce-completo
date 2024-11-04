package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro>{

	Page<Livro> findAllByAtivoTrue(Pageable paginacao);

	BigDecimal findPrecoById(Long idLivro);

	Optional<Livro> findByisbn(String isbn);
	
}
