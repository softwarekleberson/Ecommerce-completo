package br.com.engenharia.projeto.ProjetoFinal.dao.administrador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.AdministradorNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.RepositorioDeAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador.AdministradorRepository;

@Service
public class AdministradorDao implements RepositorioDeAdministrador{

	@Autowired
	private AdministradorRepository repository;
	
	public AdministradorDao(AdministradorRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Administrador adminstrador) {
		repository.save(adminstrador);
	}
	
	@Override
	public Administrador pegaAdministradorAleatorio() {
		return repository.findRandomAdministrador();
	}

	@Override
	public void deletar(Long id) {
		Optional<Administrador> existeAdministrador = repository.findById(id);
		if(existeAdministrador.isEmpty()) {
			throw new AdministradorNaoEncontradoExcecao("Administrador excluido ou id incorreto");
		}
		repository.deleteById(id);
	}

	@Override
	public boolean verificaEmailCadastrado(Email email) {
		Optional<Administrador> admEmail = repository.findByEmail(email);
		if(admEmail.isEmpty()) {
			return true;
		}		
		return false;
	}
}