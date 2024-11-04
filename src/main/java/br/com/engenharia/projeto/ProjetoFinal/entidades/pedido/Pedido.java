package br.com.engenharia.projeto.ProjetoFinal.entidades.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "pedidos")
@Entity(name = "Pedido")
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate pedidoRealizado;

    private boolean pago;

    @Transient
    private BigDecimal valorTotal;

    private String codigoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id", nullable = true)
    private Pagamento pagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientes_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    private DevolucaoFoiPedidaOUNAO trocaDevolucao;
    
    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;
    
    private LocalDate entregue;

    public Pedido(Long id, LocalDate pedidoRealizado, String codigoPedido, Cliente cliente, StatusPedido statusPedido,
                  DevolucaoFoiPedidaOUNAO trocaDevolucao, StatusEntrega statusEntrega) {
        
    	this.id = id;
        this.pago = false;
        this.pedidoRealizado = pedidoRealizado;
        this.codigoPedido = codigoPedido;
        this.cliente = cliente;
        this.statusPedido = statusPedido;
        this.trocaDevolucao = trocaDevolucao;
        this.statusEntrega = statusEntrega;
    }

    public void adicionarItem(Item item) {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }

        this.itens.add(item);
        item.setPedido(this);
    }

    public BigDecimal atualizarValorTotal() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorTotal = this.itens.stream()
                    .map(Item::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
        return valorTotal;
    }
    
    public void setEntregue(LocalDate entregue) {
		this.entregue = entregue;
	}

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void modificarStatusPedido(StatusPedido status) {
        this.statusPedido = status;
    }
    
    public void modificarStatusEntrega(StatusEntrega status) {
        this.statusEntrega = status;
    }

    public void devolucaoPedida(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
        this.trocaDevolucao = trocaDevolucao;
    }

    public void devolverItem(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
        this.trocaDevolucao = trocaDevolucao;
    }

    public void setPedidoRealizado(LocalDate pedidoRealizado) {
        this.pedidoRealizado = pedidoRealizado;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        if(valorTotal.compareTo(BigDecimal.ZERO) < 0) {
			throw new ValidacaoException("Valor do pedido não deve ser nulo");
        }
    	this.valorTotal = valorTotal;
    }

    public void setCodigoPedido(String codigoPedido) {
    	if(codigoPedido.trim() == null) {
			throw new ValidacaoException("Codigo do pedido não pode ser nulo");
    	}
    	this.codigoPedido = codigoPedido.trim().toLowerCase();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public void setTrocaDevolucao(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
        this.trocaDevolucao = trocaDevolucao;
    }
}
