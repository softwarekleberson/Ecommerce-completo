package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;

@Service
public class ServiceValidaCartao {

	public static List<Cartao> validaCartao(Long idCartao1, Long idCartao2, CartaoRepository cartaoRepository) {
		System.out.println("Service valida cartao");
		List<Cartao> cartoes = new ArrayList<>();

	    if (idCartao1 != null) {
	        Optional<Cartao> cartao1 = cartaoRepository.findById(idCartao1);
	        if (cartao1.isPresent()) {
	            cartoes.add(cartao1.get());

	        } else {
	            throw new ValidacaoPagamentoServiceException("Cartão 1 não encontrado.");
	        }
	    }

	    if (idCartao2 != null) {
	    	Optional<Cartao> cartao2 = cartaoRepository.findById(idCartao2);
	        if (cartao2.isPresent()) {
	            cartoes.add(cartao2.get());

	        } else {
	            throw new ValidacaoPagamentoServiceException("Cartão 2 não encontrado.");
	        }
	    }
	    
	    return cartoes;
	}
}
