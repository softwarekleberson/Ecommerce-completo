package br.com.engenharia.projeto.ProjetoFinal.services.seguranca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String senha;
}
