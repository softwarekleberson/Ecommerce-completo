package br.com.engenharia.projeto.ProjetoFinal.entidades.cupom;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Cupom")
@Table(name = "cupons")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cupom {

	@Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id = UUID.randomUUID().toString();
       	
    @Enumerated(EnumType.STRING)
	private TipoCupom tipoCupom;
	private BigDecimal valor;
	private boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id") 
	private Cliente cliente;
	
	public Cupom(@Valid DadosCadastroCupom dados) {
		setValor(dados.valor());
		setTipoCupom(dados.tipoCupom());
		setCliente(dados.idCliente());
		setStatus(true);
	}

	public void setValor(BigDecimal valor) {
		if(valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidacaoException("Valor não deve ser menor ou igual a zero");
		}
		this.valor = valor;
	}
	
	public void setTipoCupom(TipoCupom tipoCupom) {
		this.tipoCupom = tipoCupom;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setCliente(Long idCliente) {
		this.cliente = new Cliente();
		cliente.setId(idCliente);
	}
}
