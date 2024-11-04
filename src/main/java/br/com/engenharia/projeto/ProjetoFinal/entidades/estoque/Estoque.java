package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Estoque")
@Table(name = "estoques")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estoque {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    private Livro livro;
	
	public static final int QUANTIDADE_ESTOQUE = 0;
	private int quantidade;
	private BigDecimal valorCusto;
	private LocalDate dataEntrada;
	
	@Embedded
	private Fornecedor fornecedor;
	
	@Column(name = "estado_produto")
	@Enumerated(EnumType.STRING)
	private EstadoDoProdutoAoEntrarNoEstoque estadoDoProduto;

	public Estoque(DadosCadastroEstoque dados) {
		setQuantidade(dados.quantidade());
		setValorCusto(dados.valorCusto());
		setDataEntrada(dados.dataEntrada());
		setFornecedor(dados.fornecedor());
		setLivro(dados.idLivro());
		setEstadoDoProduto(dados.estadoProduto());
	}
	
	public void setLivro(Long id) {
		this.livro = new Livro();
		livro.setId(id);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setQuantidade(int quantidade) {
		if(quantidade < QUANTIDADE_ESTOQUE) {
			throw new ValidacaoException("Não é permitido entrada no estoque "
											 + "com quantidade de livros igual ou"
											 + " inferior a 0");
		}
		this.quantidade = quantidade;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		if(valorCusto.compareTo(BigDecimal.ZERO) < 0) {
			throw new ValidacaoException("Valor de custo não deve ser menor que "
											 + "0");
		}
		this.valorCusto = valorCusto;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public void setFornecedor(String fornecedor) {
		this.fornecedor = new Fornecedor(fornecedor);
	}
	
	public void setEstadoDoProduto(EstadoDoProdutoAoEntrarNoEstoque estadoDoProduto) {
		this.estadoDoProduto = estadoDoProduto;
	}
}