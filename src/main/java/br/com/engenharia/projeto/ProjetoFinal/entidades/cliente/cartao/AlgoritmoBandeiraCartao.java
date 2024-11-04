package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

public class AlgoritmoBandeiraCartao {

	private final static int VISA_MASTERCARD_LENGHT = 16;
	private final static int ELO_MAXIMO_LENGHT = 19;
	private final static int ELO_MINIMO_LENGHT = 16;

	public static void bandeiraCartao(String numeroCartao, Bandeira bandeira) {
	    
	    if (bandeira == Bandeira.MASTERCARD || bandeira == Bandeira.VISA) {
	        if (numeroCartao.length() != VISA_MASTERCARD_LENGHT) {
	            throw new ValidacaoException("Mastercard ou Visa precisam conter 16 digitos");
	        }
	    } else if (bandeira == Bandeira.ELO) {
	        if (!(numeroCartao.length() >= ELO_MINIMO_LENGHT && numeroCartao.length() <= ELO_MAXIMO_LENGHT)) {
	            throw new ValidacaoException("Elo precisa conter entre 16 ou 19 digitos");
	        }
	    }
	}
}