package br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import jakarta.transaction.Transactional;

public interface EntregaRepository extends JpaRepository<Entrega, Long>{

	Page<Entrega> findByCliente_Id(Long clienteId, Pageable pageable);

	@Transactional
    @Modifying
    void deleteByCliente_Id(Long clienteId);

    @Modifying
    @Transactional
    @Query("UPDATE Entrega e SET e.principal = false WHERE e.cliente.id = :id")
	void atualizarEntregaPrincipal(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Entrega e SET e.principal = (e.id = :id) WHERE e.cliente.id = :id2")
    void atualizarEntregasNaoPrincipalClienteExceptIdAndPrincipal(Long id, Long id2);

    @Modifying
    @Transactional
    @Query("UPDATE Entrega e SET e.principal = true WHERE e.id = :entregaId")
    void updateEntregaPrincipal(Long entregaId);

    @Query("SELECT e FROM Entrega e WHERE e.cliente.id = :idCliente AND e.principal = true AND e.ativo = true")
    Optional<Entrega> findEntregaPrincipalByClienteId(@Param("idCliente") Long idCliente);
}