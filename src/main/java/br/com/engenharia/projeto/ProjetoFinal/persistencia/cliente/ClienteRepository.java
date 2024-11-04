package br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findAllByAtivoTrue(Pageable paginacao);
    Optional<Cliente> findByCpf(String itin);
    Cliente findByIdAndAtivoTrue(Long id);
}
