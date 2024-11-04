package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import java.util.stream.Stream;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
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
@Table(name = "cobrancas")
public class Cobranca extends Endereco{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	
	private boolean principal;
	private boolean ativo = true;
	
	public Cobranca(@Valid DadosCadastroCobranca dados) {
		super(dados);
		setAtivo(true);
		setPrincipal(dados.principal());
		setCliente(dados.idCliente());
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

	public Stream<Pedido> stream() {
		// TODO Auto-generated method stub
		return null;
	}
}