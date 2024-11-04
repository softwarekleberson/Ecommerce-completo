package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.Precificacao;

public interface PrecificacaoRepository extends JpaRepository<Precificacao, Long>{

}
