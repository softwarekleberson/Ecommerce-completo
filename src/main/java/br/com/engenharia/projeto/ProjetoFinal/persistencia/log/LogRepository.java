package br.com.engenharia.projeto.ProjetoFinal.persistencia.log;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

}
