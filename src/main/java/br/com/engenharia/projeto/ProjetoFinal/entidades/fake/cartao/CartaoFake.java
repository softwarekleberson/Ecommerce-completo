package br.com.engenharia.projeto.ProjetoFinal.entidades.fake.cartao;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cartao_fake")
public class CartaoFake {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cartao", length = 16, nullable = false)
    private String numeroCartao;

    @Column(name = "nome_titular", nullable = false)
    private String nomeTitular;

    @Column(name = "limite_credito", precision = 10, scale = 2, nullable = false)
    private BigDecimal limiteCredito;

    @Column(name = "saldo_disponivel", precision = 10, scale = 2, nullable = false)
    private BigDecimal saldoDisponivel;

    @Column(name = "is_ativo", nullable = false)
    private Boolean isAtivo = true;

    @Column(name = "is_simulado", nullable = false)
    private Boolean isSimulado = true;
    
    public void subtraiValorDoLimite(BigDecimal valorPedido) {
    	this.saldoDisponivel.subtract(valorPedido);
    }

}
