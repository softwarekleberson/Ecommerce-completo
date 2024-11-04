package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

@Service
public class ValidaSenhaAtualizadaCliente implements IStrategySenhaAtualizadaCliente {

    private static final String MENSAGEM_ERRO_FORMATO_SENHA = "Senha deve conter pelo menos\"\n"
    		+ "            							+ \" uma letra maiúscula, uma\"\n"
    		+ "            							+ \" letra minúscula, um número e um caractere especial";
	
    private static final String MENSAGEM_ERRO_QUANTIDADE_MINIMA = "Senha deve ter no mínimo 8 caracteres";
    
	@Override
    public void processar(DadosAtualizacaoSenha dados) {
		String senha = dados.senha();
        if (senha.length() < 8) {
            throw new ValidacaoException(MENSAGEM_ERRO_QUANTIDADE_MINIMA);
        }

        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]).*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(senha);

        if (!matcher.matches()) {
            throw new ValidacaoException(MENSAGEM_ERRO_FORMATO_SENHA);
        }
    }
}