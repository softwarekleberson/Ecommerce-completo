package br.com.engenharia.projeto.ProjetoFinal.entidades.item;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Entity(name = "Item")
@Table(name = "itens")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int quantidade;
	 
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	 
	private BigDecimal subtotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "livros_id")
	private Livro livro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedidos_id")
	private Pedido pedido;

	public Item(Long id, int quantidade, BigDecimal precoUnitario, Livro livro, Pedido pedido) {
		this.id = id;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.livro = livro;
		this.pedido = pedido;
	}
		
	public Item() {}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
}