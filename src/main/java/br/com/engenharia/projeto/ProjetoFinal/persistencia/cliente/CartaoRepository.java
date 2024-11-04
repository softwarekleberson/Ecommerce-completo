package br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import jakarta.transaction.Transactional;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	Page<Cartao> findAllByAtivoTrue(Pageable paginacao);

	Streamable<Cartao> findAllByClienteIdAndAtivoTrue(Long clienteId);

    Page<Cartao> findByCliente_Id(Long clienteId, Pageable pageable);

    @Transactional
    @Modifying
    void deleteByCliente_Id(Long clienteId);

    @Modifying
    @Transactional
    @Query("UPDATE Cartao c SET c.principal = (c.id = :cartaoId) WHERE c.cliente.id = :id")
    void atualizarCartoesNaoPrincipalClienteExceptIdAndPrincipal(Long id, Long cartaoId);

    @Modifying
    @Transactional
    @Query("UPDATE Cartao c SET c.principal = false WHERE c.cliente.id = :id")
	void atualizarCartoesNaoPrincipalCliente(Long id);

	Optional<Cartao> findBynumeroCartao(String numeroCartao);
}
