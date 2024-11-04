package br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "devolucoes")
@Entity(name = "Devolucao")
@EqualsAndHashCode(of = "id")
public class Devolucao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigoDevolucao;
	
	private LocalDate dataPedidoTroca;
	
	private LocalDate dataConclusaoTroca;
	
	@ManyToOne
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "pedidos_id")
	private Pedido pedido;
	
	private String codigoPedido;
	
	@ManyToOne
	@JoinColumn(name = "administradores_id")
	private Administrador administrador;
	
	@Column(name = "esperando_devolucao")
	@Enumerated(EnumType.STRING)
	private EsperandoDevolucaoOuRecebido esperandoDevolucaoOuRecebido;
		
	@Column(name = "analise_devolucao")
	@Enumerated(EnumType.STRING)
	private AnalisePedidoDevolucaoAceitoOuRecusa analisePedido;
	
	public void devoluvaoChegou(EsperandoDevolucaoOuRecebido esperandoDevolucaoOuRecebido) {
		this.esperandoDevolucaoOuRecebido = esperandoDevolucaoOuRecebido;
	}
	
	public void analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa analisePedido) {
		this.analisePedido = analisePedido;
	}
}