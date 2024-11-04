package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.CriptografiaSenhaAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosDetalhamentoAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.RepositorioDeAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;
import jakarta.validation.Valid;

@Service
public class ServiceAdministrador {

	@Autowired
	private CriptografiaSenhaAdministrador criptografia;
	
	@Autowired
	private RepositorioDeAdministrador repositorioDeAdministrador;
	
	public DadosDetalhamentoAdministrador criarAdministrador(@Valid DadosCadastroAdministrador dados) {
		
		Email email = new Email(dados.email());
		boolean emailCadastrado = repositorioDeAdministrador.verificaEmailCadastrado(email);
		if(!emailCadastrado) {
			throw new IllegalArgumentException("Email cadastrado anteriormente");
		}
		
		Administrador administrador = new Administrador(dados);
		criptografia.processar(administrador);
		
		repositorioDeAdministrador.salvar(administrador);
		
		return new DadosDetalhamentoAdministrador(administrador);
	}
}