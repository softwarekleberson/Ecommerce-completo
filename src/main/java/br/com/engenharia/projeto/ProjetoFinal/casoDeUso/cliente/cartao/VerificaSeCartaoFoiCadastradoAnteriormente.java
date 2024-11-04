package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.RepositorioDeCartao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

@Service
public class VerificaSeCartaoFoiCadastradoAnteriormente implements IstrategyValidaCartao{

	private static final String MENSAGEM_ERRO = "Cartão cadastrado anteriormente";
	
	@Autowired
	private RepositorioDeCartao repositorioDeCartao;
	
	@Override
	public void processar(DadosCadastroCartao dados) {
		Optional<Cartao> cartao = repositorioDeCartao.cartaoCadastradoAnteriormente(dados.numeroCartao());
		if(cartao.isPresent() && cartao.get().getNumeroCartao().equals(dados.numeroCartao()) && cartao.get().getBandeira().equals(dados.bandeira())) {
			throw new ValidacaoException(MENSAGEM_ERRO);
		}
	}
}