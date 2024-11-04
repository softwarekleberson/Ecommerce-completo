package br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Pagamento")
@Table(name = "pagamentos")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

     @Id
     @Column(name = "id", updatable = false, nullable = false)
     private String id = UUID.randomUUID().toString();
        
	 private LocalDateTime dataPagamento;
	    
	 @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JoinColumn(name = "entrega_id")
	 private Entrega entrega;

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JoinColumn(name = "cobranca_id")
     private Cobranca cobranca;
	      
	 @Column(name = "valor_total")
	 private BigDecimal valorTotal;
	    
	 @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Pedido> pedidos;
	    
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JoinColumn(name = "pagamento_id", nullable = true)
	 private List<Cartao> cartoes;

	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JoinColumn(name = "pagamento_id", nullable = true)
	 private List<Cupom> cupons;
	    
	 @Column(name = "status_compra")
	 @Enumerated(EnumType.STRING)
	 private StatusCompra statusCompra;
	 
	 public Pagamento() {}

	 public void mudarStatusPagamento(StatusCompra statusCompra) {
	    this.statusCompra = statusCompra;
	  }

	public Pagamento(LocalDateTime dataPagamento, Entrega entrega, Cobranca cobranca, BigDecimal valorTotalPedido,
			List<Pedido> pedidos, List<Cartao> cartoes, List<Cupom> cupons, StatusCompra statusCompra) {
		
		this.dataPagamento = dataPagamento;
		this.entrega = entrega;
		this.cobranca = cobranca;
		this.valorTotal = valorTotalPedido;
		this.pedidos = pedidos;
		this.cartoes = cartoes;
		this.cupons = cupons;
		this.statusCompra = statusCompra;
		
	}
}
