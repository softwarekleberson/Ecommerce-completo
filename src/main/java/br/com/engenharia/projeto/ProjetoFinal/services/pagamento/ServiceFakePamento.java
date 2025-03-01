package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.TipoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.fake.cartao.CartaoFake;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cartaoFake.FakeRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pagamento.PagamentoRepository;

@Service
public class ServiceFakePamento {

	 public static void apiDePagamentoFake(Pagamento pagamento, Long idCartao1, Long idCartao2, String idCupom1, String idCupom2, CartaoRepository cartaoRepository, FakeRepository fakeRepository, CupomRepositroy cupomRepository, PagamentoRepository pagamentoRepository) {
			System.out.println("Service api de pagamento fake");

		 	BigDecimal valorDoCartao = BigDecimal.ZERO;
	        BigDecimal valorDoCupom = BigDecimal.ZERO;

	        if (idCartao1 != null) {
	            Optional<Cartao> cartaoCorrente = cartaoRepository.findById(idCartao1);
	            Optional<CartaoFake> cartao1 = fakeRepository.findByNumeroCartao(cartaoCorrente.get().getNumeroCartao());
	            if (cartao1.isPresent()) {
	                valorDoCartao = valorDoCartao.add(cartao1.get().getSaldoDisponivel());
	            } else {
	                throw new ValidacaoPagamentoServiceException("Cartão 1 não encontrado");
	            }
	        }

	        if (idCartao2 != null) {
	            Optional<Cartao> cartaoCorrente2 = cartaoRepository.findById(idCartao2);
	            Optional<CartaoFake> cartao2 = fakeRepository.findByNumeroCartao(cartaoCorrente2.get().getNumeroCartao());
	            if (cartao2.isPresent()) {
	                valorDoCartao = valorDoCartao.add(cartao2.get().getSaldoDisponivel());
	            } else {
	                throw new ValidacaoPagamentoServiceException("Cartão 2 não encontrado");
	            }
	        }

	        if (idCupom1 != null) {
	            Optional<Cupom> cupom1 = cupomRepository.findById(idCupom1);
	            if (cupom1.isPresent()) {
	                valorDoCupom = valorDoCupom.add(cupom1.get().getValor());
	            } else {
	                throw new ValidacaoPagamentoServiceException("Cupom 1 não encontrado");
	            }
	        }

	        if (idCupom2 != null) {
	            Optional<Cupom> cupom2 = cupomRepository.findById(idCupom2);
	            if (cupom2.isPresent()) {
	                valorDoCupom = valorDoCupom.add(cupom2.get().getValor());
	            } else {
	                throw new ValidacaoPagamentoServiceException("Cupom 2 não encontrado");
	            }
	        }

	        mudancaStatusPagamento(pagamento, valorDoCartao, valorDoCupom, idCupom1, idCupom2, pagamentoRepository, cupomRepository);
	        verificaNecessidadeNovoCupom(valorDoCupom, pagamento, cupomRepository);
	    }

	    private static void desabilitaCupons(String idCupom1, String idCupom2, CupomRepositroy cupomRepository) {
	        if (idCupom1 != null) {
	            Optional<Cupom> cupom1 = cupomRepository.findById(idCupom1);
	            cupom1.ifPresent(c -> c.setStatus(false));
	        }

	        if (idCupom2 != null) {
	            Optional<Cupom> cupom2 = cupomRepository.findById(idCupom2);
	            cupom2.ifPresent(c -> c.setStatus(false));
	        }
	    }

	    private static void mudancaStatusPagamento(Pagamento pagamento, BigDecimal valorDoCartao, BigDecimal valorDoCupom, String idCupom1, String idCupom2, PagamentoRepository pagamentoRepository, CupomRepositroy cupomRepository) {
	        BigDecimal somaDosMetodosPagamento = valorDoCartao.add(valorDoCupom);
	        if (somaDosMetodosPagamento.compareTo(pagamento.getValorTotal()) >= 0) {
	            desabilitaCupons(idCupom1, idCupom2, cupomRepository);
	            pagamento.setStatusCompra(StatusCompra.APROVADO);
	        } else {
	            pagamento.setStatusCompra(StatusCompra.REPROVADO);
	        }
	        pagamentoRepository.saveAndFlush(pagamento);
	    }

	    private static void verificaNecessidadeNovoCupom(BigDecimal valorDoCupom, Pagamento pagamento, CupomRepositroy cupomRepository) {
	        if (valorDoCupom.compareTo(pagamento.getValorTotal()) > 0) {
	            BigDecimal valorNovoCupom = valorDoCupom.subtract(pagamento.getValorTotal());
	            Cliente cliente = pagamento.getPedidos().get(0).getCliente();
	            Cupom cupom = new Cupom(UUID.randomUUID().toString(), TipoCupom.PROMOCIONAL, valorNovoCupom, true, cliente);
	            cupomRepository.save(cupom);
	        }
	    }
}
