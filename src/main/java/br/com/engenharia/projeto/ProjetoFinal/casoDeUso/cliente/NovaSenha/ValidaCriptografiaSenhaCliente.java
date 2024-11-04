package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;

@Service
public class ValidaCriptografiaSenhaCliente implements CriptografaSenhaCliente{
		
	public void processar(Cliente dominio) {
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		    String senhaCriptografada = passwordEncryptor.encryptPassword(dominio.getSenha());
			dominio.CriptografarSenha(senhaCriptografada);		    		
	}
}