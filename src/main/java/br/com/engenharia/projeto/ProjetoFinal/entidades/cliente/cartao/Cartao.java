package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cartoes")
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private boolean principal;
	
	private String nomeImpresso;
	
	public static final int CODIGO_CARTAO_CREDITO = 3;
	private String codigo;
	
	public static final int NUMERO_CARTAO_MINIMO = 16;
	public static final int NUMERO_CARTAO_MAXIMO = 19;
	private String numeroCartao;
	
	@Enumerated(EnumType.STRING)
	private Bandeira bandeira;
	
	private LocalDate dataExpiracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	private boolean ativo;
	
	public Cartao(@Valid DadosCadastroCartao dados) {
		
		algoritmoLumn(dados.numeroCartao());
		algoritmoBandeira(dados.numeroCartao(), dados.bandeira());
		
		setPrincipal(dados.principal());
		setBandeira(dados.bandeira());
		setCodigo(dados.codigo());
		setNomeImpresso(dados.nomeImpresso());
		setNumeroCartao(dados.numeroCartao());
		setDataExpiracao(dados.dataExpiracao());
		setCliente(dados.idCliente());
		setAtivo(true);
	}
	
	public void algoritmoLumn(String numeroCartao) {
		AlgoritmoLuhn.algoritmoLuhn(numeroCartao); 
	}
	
	public void algoritmoBandeira(String numeroCartao, Bandeira bandeira) {
		AlgoritmoBandeiraCartao.bandeiraCartao(numeroCartao, bandeira);
	}

	public static int getNumeroCartaoMaximo() {
		return NUMERO_CARTAO_MAXIMO;
	}
	
	public static int getNumeroCartaoMinimo() {
		return NUMERO_CARTAO_MINIMO;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setCliente(Long idClinte) {
		this.cliente = new Cliente();
		cliente.setId(idClinte);
	}
	
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	public void setNomeImpresso(String nomeImpresso) {
		if(nomeImpresso == null || nomeImpresso.trim().isEmpty() || nomeImpresso.length() <= 2) {
			throw new ValidacaoException("Nome deve possuir mais de 2 digitos");
		}
		this.nomeImpresso = nomeImpresso.trim().toLowerCase();
	}
	
	public void setCodigo(String codigo) {
	    if(codigo.trim().length() != CODIGO_CARTAO_CREDITO) {
	        throw new ValidacaoException("Codigo do cartão deve conter 3 digitos");
	    }
	    this.codigo = codigo.trim().toLowerCase();
	}
	
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	
	public void setNumeroCartao(String numeroCartao) {
	    this.numeroCartao = numeroCartao.trim().toLowerCase();
	}
	
	public void setDataExpiracao(LocalDate dataExpiracao) {
		if(dataExpiracao.isBefore(LocalDate.now())) {
			throw new ValidationException("Data de expiração não deve ser no passado");
		}
		this.dataExpiracao = dataExpiracao;
	}
}