package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;

@Service
public class CriptografarSenhaAdministrador implements CriptografiaSenhaAdministrador{

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public void processar(Administrador dominio) {
		
	    String senhaCriptografada = passwordEncoder.encode(dominio.getSenha());
		dominio.CriptografarSenha(senhaCriptografada);	
	}
}