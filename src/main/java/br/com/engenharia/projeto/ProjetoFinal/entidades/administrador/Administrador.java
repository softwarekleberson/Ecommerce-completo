package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.Roles;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity(name = "Administrador")
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Administrador extends UserEntity{
	
	private static final long serialVersionUID = 1L;

	public Administrador(DadosCadastroAdministrador dados) {
		super(dados.nome(),dados.email(),dados.senha(),dados.confirmarSenha());
		setRoles(Roles.ADMIN);
	}
}