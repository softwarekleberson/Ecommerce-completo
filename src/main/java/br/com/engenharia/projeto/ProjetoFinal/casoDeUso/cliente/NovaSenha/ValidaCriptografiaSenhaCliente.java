package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;

@Service
public class ValidaCriptografiaSenhaCliente implements CriptografaSenhaCliente{
		
	 private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	 public void processar(Cliente dominio) {
	     String senhaCriptografada = passwordEncoder.encode(dominio.getSenha());
	     dominio.CriptografarSenha(senhaCriptografada);
	 }
}