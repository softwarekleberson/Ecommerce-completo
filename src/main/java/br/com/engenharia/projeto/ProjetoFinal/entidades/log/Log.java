package br.com.engenharia.projeto.ProjetoFinal.entidades.log;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Log")
@Table(name = "logs")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idCliente;
	private LocalDateTime dataHora;
	
	public Log(Long idCliente) {
		this.idCliente = idCliente;
		setDataHora(LocalDateTime.now());
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}