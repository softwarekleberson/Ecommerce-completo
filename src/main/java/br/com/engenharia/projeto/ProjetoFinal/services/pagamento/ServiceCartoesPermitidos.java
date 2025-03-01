package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.fake.cartao.CartaoFake;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cartaoFake.FakeRepository;

@Service
public class ServiceCartoesPermitidos {

	public static void checarQuantidadeCartoesPermitida(BigDecimal valorTotalPedido, List<Cartao> cartoes,
	    Long cartao1, Long cartao2, FakeRepository fakeRepository) {
	
		System.out.println("Service Cartoes permitidos");
		BigDecimal saldoCartao1 = BigDecimal.ZERO;
		BigDecimal saldoCartao2 = BigDecimal.ZERO;

		if(cartoes.size() == 2) {
			if(cartao1 != null) {
				Optional<CartaoFake> card1 = fakeRepository.findById(cartao1);
				if(card1.isPresent()) {
					saldoCartao1 = saldoCartao1.add(card1.get().getSaldoDisponivel()); 
				}
			}
			
			if(cartao2 != null) {
				Optional<CartaoFake> card2 = fakeRepository.findById(cartao2);
				if(card2.isPresent()) {
					saldoCartao2 = saldoCartao2.add(card2.get().getSaldoDisponivel()); 
				}
			}
			
			if(saldoCartao1.compareTo(BigDecimal.TEN) < 0 && saldoCartao2.compareTo(BigDecimal.TEN) < 0) {
				throw new ValidacaoPagamentoServiceException("Não se pode usar dois cartões e o valor a ser pago com os"
											+ " dois cartões ser menor que 10 reais em cada cartão");	
			}
		}
	}
}
