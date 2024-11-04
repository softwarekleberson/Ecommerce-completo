package br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import jakarta.transaction.Transactional;

public interface CobrancaRepository extends JpaRepository<Cobranca, Long>{

	Page<Cobranca> findByCliente_Id(Long clienteId, Pageable pageable);

	@Transactional
    @Modifying
    void deleteByCliente_Id(Long clienteId);

	@Modifying
    @Transactional
    @Query("UPDATE Cobranca c SET c.principal = false WHERE c.cliente.id = :id")
	void atualizarCobrancaPrincipal(Long id);

	@Modifying
    @Transactional
	@Query("UPDATE Cobranca c SET c.principal = (c.id = :id) WHERE c.cliente.id = :id2")
	void atualizarCobrancasNaoPrincipalClienteExceptIdAndPrincipal(Long id, Long id2);

	Cobranca findByPrincipalTrue();

}
