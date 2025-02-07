package br.com.engenharia.projeto.ProjetoFinal.persistencia.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);
}
