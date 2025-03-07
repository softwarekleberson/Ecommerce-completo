package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao;

public class AlgoritmoLuhn {

    private static final String MENSAGEM_ERRO = "O número do seu cartão é inválido.";
	
	public static void algoritmoLuhn(String numero) {
		
		 int sum = 0;
	     boolean alternar = false;

	        for (int i = numero.length() - 1; i >= 0; i--) {
	            int n = Integer.parseInt(numero.substring(i, i + 1));
	            if (alternar) {
	                n *= 2;
	                if (n > 9) {
	                    n -= 9;
	                }
	            }
	            sum += n;
	            alternar = !alternar;
	        }

	        if (sum % 10 != 0) {
	            throw new ValidacaoCartaoException(String.format(MENSAGEM_ERRO));
	        }
	}
}