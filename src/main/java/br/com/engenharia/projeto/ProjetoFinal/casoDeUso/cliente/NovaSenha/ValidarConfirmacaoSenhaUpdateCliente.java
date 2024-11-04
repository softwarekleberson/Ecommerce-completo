package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

@Service
public class ValidarConfirmacaoSenhaUpdateCliente implements IStrategySenhaAtualizadaCliente{

    private static final String MENSAGEM_ERRO = "primeira senha não corresponde a confirmar senha";
	
	@Override
	public void processar(DadosAtualizacaoSenha dados) {
		
		if(!dados.senha().matches(dados.confirmarSenha())) {
			throw new ValidacaoException(MENSAGEM_ERRO);
		}		
	}
}