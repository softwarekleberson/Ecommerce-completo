package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

import java.util.regex.Pattern;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

public class AlgoritmoVerificaFormatoSenha {

    private static final String MENSAGEM_ERRO_FORMATO_INCORRETO = 
        "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e ter no mínimo 20 caracteres.";

    private static final Pattern REGEX_SENHA = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$"
    );

    public static void algoritmoVerificaFormatoSenha(String senha) {
        if (!REGEX_SENHA.matcher(senha).matches()) {
            throw new ValidacaoException(MENSAGEM_ERRO_FORMATO_INCORRETO);
        }
    }
}