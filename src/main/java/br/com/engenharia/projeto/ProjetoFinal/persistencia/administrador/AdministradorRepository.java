package br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	Optional<Administrador> findByEmail(String email);

	@Query(value = """
		    SELECT u.id, u.nome, u.email, u.senha, u.roles,
		           a.id AS administrador_id
		    FROM administradores a
		    JOIN user_entity u ON a.id = u.id
		    WHERE u.roles = 'ADMIN'
		    ORDER BY RAND()
		    LIMIT 1
		    """, nativeQuery = true)
	Administrador findAdminRand();
}
