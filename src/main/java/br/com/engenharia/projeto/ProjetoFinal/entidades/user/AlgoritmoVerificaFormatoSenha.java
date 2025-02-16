package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

public class AlgoritmoVerificaFormatoSenha {

    private static final String MENSAGEM_ERRO_QUANTIDADE_MINIMA = "Senha deve ter no mínimo 8 caracteres.";
    private static final String MENSAGEM_ERRO_FORMATO_INCORRETO = "Senha deve conter pelo menos uma letra maiúscula, "
            + "uma letra minúscula, um número e um caractere especial.";

    private static final String REGEX_SENHA = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?/]).{8,}$";

    public static void algoritmoVerificaFormatoSenha(String senha) {
        if (senha.length() < 8) {
            throw new ValidacaoException(MENSAGEM_ERRO_QUANTIDADE_MINIMA);
        }

        String senhaEscapada = Pattern.quote(senha);

        Pattern regex = Pattern.compile(REGEX_SENHA);
        Matcher matcher = regex.matcher(senhaEscapada);

        if (!matcher.matches()) {
            throw new ValidacaoException(MENSAGEM_ERRO_FORMATO_INCORRETO);
        }
        
     }
}
