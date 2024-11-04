package br.com.engenharia.projeto.ProjetoFinal.persistencia.devolucao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;

public interface DevolucaoRepository extends JpaRepository<Devolucao, Long>{

	Page<Devolucao> findByAdministrador_Id(Long admId, Pageable pageable);

	Optional<Devolucao> findByCodigoDevolucao(String codigoDevolucao);

	Optional<Devolucao> findByCodigoPedido(String codigoDevolucao);

}
