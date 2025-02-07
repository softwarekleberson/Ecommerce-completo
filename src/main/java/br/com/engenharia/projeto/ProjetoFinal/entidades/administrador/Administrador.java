package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.Roles;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity(name = "Administrador")
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id")
public class Administrador extends UserEntity{
	
	public Administrador(DadosCadastroAdministrador dados, Roles roles) {
		super(dados.nome(),dados.email(),dados.senha(),dados.confirmarSenha());
		setRole(Roles.ADM);
	}
}