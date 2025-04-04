package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;

@Service
public class ValidarConfirmacaoSenhaUpdateCliente implements IStrategySenhaAtualizadaCliente{

    private static final String MENSAGEM_ERRO = "primeira senha não corresponde a confirmar senha";
	
	@Override
	public void processar(DadosAtualizacaoSenha dados) {
		if(!dados.senha().equals(dados.confirmarSenha())) {
			throw new ValidacaoSenhaException(MENSAGEM_ERRO);
		}		
	}
}