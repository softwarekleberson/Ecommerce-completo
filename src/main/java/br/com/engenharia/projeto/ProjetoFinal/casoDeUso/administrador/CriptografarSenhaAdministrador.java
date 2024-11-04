package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;

@Service
public class CriptografarSenhaAdministrador implements CriptografiaSenhaAdministrador{

	@Override
	public void processar(Administrador dominio) {
		
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
	    String senhaCriptografada = passwordEncryptor.encryptPassword(dominio.getSenha());
		dominio.CriptografarSenha(senhaCriptografada);	
	}
}