package br.com.engenharia.projeto.ProjetoFinal.persistencia.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
