package br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	Optional<Administrador> findByEmail(Email email);

    @Query
    (value = "SELECT * FROM Administradores"
    + " ORDER BY RAND() LIMIT 1",
    nativeQuery = true)
	Administrador findRandomAdministrador();

}
