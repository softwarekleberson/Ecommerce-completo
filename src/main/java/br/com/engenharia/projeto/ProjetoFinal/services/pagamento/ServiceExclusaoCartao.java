package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;

@Service
public class ServiceExclusaoCartao {

	public static void cartaoSeraSalvo(Boolean salvarCartao, List<Cartao> cartoes, CartaoRepository cartaoRepository) {
		System.out.println("Service exclusão cartão");
		if(salvarCartao != true) {
			for(Cartao cartao : cartoes) {
				cartaoRepository.delete(cartao);
			}
		}
	}
}