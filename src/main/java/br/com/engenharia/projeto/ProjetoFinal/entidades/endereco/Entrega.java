package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entregas")
public class Entrega extends Endereco{

	private boolean principal;
	private String fraseEntrega;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	
	private boolean ativo;
	
	public Entrega(@Valid DadosCadastroEntrega dados) {
		super(dados);
		setCliente(dados.idCliente());
		setPrincipal(dados.principal());
		setFraseEntrega(dados.fraseEntregaEntrega());
		setAtivo(true);
	}

	public void setCliente(Long idCliente) {
	    Cliente cliente = new Cliente();
	    cliente.setId(idCliente);
	    this.cliente = cliente;
	}
	
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setFraseEntrega(String fraseEntrega) {
		if(fraseEntrega == null || fraseEntrega.trim().length() == 0) {
			throw new ValidacaoException("Frase de entrega não deve ser nulo");
		}
		this.fraseEntrega = fraseEntrega.trim().toLowerCase();
	}
}