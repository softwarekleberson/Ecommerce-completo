package br.com.engenharia.projeto.ProjetoFinal.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Bandeira;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.ValidacaoCartaoException;

public class CartaoTest {

	private DadosCadastroCartao dadosCadastroCartao;
	
	@BeforeEach
	void setUp() {
		dadosCadastroCartao = new
	    DadosCadastroCartao (true, "5041759102893600",
	    					"nome valido", "555",
	    					Bandeira.MASTERCARD,
	    					LocalDate.now().plusYears(3));
	}
		
	@Test
	void deveLancarExcecaoQuandoLumnContrariado() {
		dadosCadastroCartao =
		new DadosCadastroCartao (true, "3586307754442000",
						"Ana maria", "555",
						Bandeira.MASTERCARD,
						LocalDate.now().plusYears(3));
	
		Exception exception = assertThrows(ValidacaoCartaoException.class, () ->{
			new Cartao(dadosCadastroCartao);
		});
		
		assertEquals("O número do seu cartão é inválido.", exception.getMessage());
	}
	
	@Test
	void deveLancarExcecaoQuandoDigitoSegurancaIgualInferiorDoisDigitos() {
		dadosCadastroCartao =
		new DadosCadastroCartao (true, "3586307754442742",
						"Ana maria", "55",
						Bandeira.MASTERCARD,
						LocalDate.now().plusYears(3));
	
		Exception exception = assertThrows(ValidacaoCartaoException.class, () ->{
			new Cartao(dadosCadastroCartao);
		});
		
		assertEquals("Codigo do cartão deve conter 3 digitos", exception.getMessage());
	}
	
	@Test
	void deveLancarExcecaoQuandoNomeIgualOuInferiorDoisDigitos() {
		dadosCadastroCartao =
		new DadosCadastroCartao (true, "3586307754442742",
						"Na", "555",
						Bandeira.MASTERCARD,
						LocalDate.now().plusYears(3));
		
		Exception exception = assertThrows(ValidacaoCartaoException.class, () -> {
			new Cartao(dadosCadastroCartao);
		});
		
		assertEquals("Nome deve possuir mais de 2 digitos", exception.getMessage());
	}
	
	@Test
	void deveLancarExcecaoQuandoDataExpiracaoNoPassado() {
		dadosCadastroCartao = 
	    new DadosCadastroCartao (true, "3586307754442742",
	    						"Nome valido", "555",
	    						Bandeira.MASTERCARD,
	    						LocalDate.of(2025, 03, 06));
	
		 Exception exception = assertThrows(ValidacaoCartaoException.class, () -> {
		    new Cartao(dadosCadastroCartao);
		 });
		
		assertEquals("Data de expiração não deve ser no passado", exception.getMessage());
	}
	
	@Test
	void deveSetarClienteCorretamente() {
		Cartao cartao = new Cartao(dadosCadastroCartao);
		cartao.setCliente(1L);
		
		assertNotNull(cartao.getCliente().getId());
		assertEquals(1L, cartao.getCliente().getId());
		
	}
}
