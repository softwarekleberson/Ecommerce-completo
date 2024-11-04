package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.validation.ValidationException;

public class AlgoritmoVerificaSenhaAdm {

	private static final String SENHA_CURTA = "Senha deve ter no mínimo 8 caracteres";
    private static final String SENHA_INVALIDA = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial";
    private static final String REGEX_SENHA = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]).*$";

    public static void algoritmoVerificaSenhaAdm(String senha) {
        
        if (senha.length() < 8) {
            throw new ValidacaoException(SENHA_CURTA);
        }

        Pattern regex = Pattern.compile(REGEX_SENHA);
        Matcher matcher = regex.matcher(senha);

        if (!matcher.matches()) {
            throw new ValidationException(SENHA_INVALIDA);
        }
    }
}