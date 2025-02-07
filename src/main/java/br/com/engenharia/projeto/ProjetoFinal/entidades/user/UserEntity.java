package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.AlgoritmoVerificaSenha;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.AlgoritmoVerificaFormatoSenha;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.*;

@Entity
@Table(name = "user_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Email email;
	private String senha;
	
	@Transient
	private String confirmarSenha;
	
	@Enumerated(EnumType.STRING)
	private Roles roles;

	public UserEntity(String nome, String email, String senha, String confirmarSenha) {
		
		algoritmoVerificaFormatoSenha(senha);
		algoritmoConfirmacaoSenha(senha, confirmarSenha);
		
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setConfirmarSenha(confirmarSenha);
	}

	public UserEntity() {
	}

	public void algoritmoVerificaFormatoSenha(String senha) {
		AlgoritmoVerificaFormatoSenha.algoritmoVerificaFormatoSenha(senha);
	}
	
	public void algoritmoConfirmacaoSenha(String senha, String confirmacaoSenha) {
		AlgoritmoVerificaSenha.algoritmoVerificaSenha(senha, confirmacaoSenha);
	}
	
	public void CriptografarSenha(String senhaCriptografada) {
		this.senha = senhaCriptografada;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome.trim() == null) {
			throw new ValidacaoException("Nome não pode ser nulo");
		}
		this.nome = nome.trim().toLowerCase();
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = new Email(email);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRole(Roles roles) {
		this.roles = roles;
	}
}
