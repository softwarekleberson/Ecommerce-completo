package br.com.engenharia.projeto.ProjetoFinal.dao.administrador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.AdministradorDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.RepositorioDeAdministrador;
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
		System.out.println("qqqqqqqqqqqq");
		return repository.findAdminRand();
	}

	@Override
	public void deletar(Long id) {
		Optional<Administrador> existeAdministrador = repository.findById(id);
		if(existeAdministrador.isEmpty()) {
			throw new AdministradorDaoExcecao("Administrador excluido ou id incorreto");
		}
		repository.deleteById(id);
	}

	@Override
	public boolean verificaEmailCadastrado(String email) {
		Optional<Administrador> admEmail = repository.findByEmail(email);
		if(admEmail.isEmpty()) {
			return true;
		}		
		return false;
	}
}