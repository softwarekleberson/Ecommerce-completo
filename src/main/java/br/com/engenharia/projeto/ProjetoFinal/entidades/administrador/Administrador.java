package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "administradores")
@Entity(name = "Administrador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Email email;
	private String senha;
	
	@Transient
	private String confirmarSenha;
	
	public Administrador(DadosCadastroAdministrador dados) {
		
		algoritmoVerificaSenhaAdm(dados.senha());
		algoritmoConfirmacaoSenha(dados.senha(), dados.confirmarSenha());
		
		setNome(dados.nome());
		setEmail(dados.email());
		setSenha(dados.senha());
		setConfirmarSenha(dados.confirmarSenha());
	}
	
	public void algoritmoVerificaSenhaAdm(String senha) {
		AlgoritmoVerificaSenhaAdm.algoritmoVerificaSenhaAdm(senha);
	}
	
	public void algoritmoConfirmacaoSenha(String senha, String confirmacaoSenha) {
		AlgoritmoVerificaSenha.algoritmoVerificaSenha(senha, confirmacaoSenha);
	}
	
	public void CriptografarSenha(String senhaCriptografada) {
		this.senha = senhaCriptografada;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		if(nome.trim() == null) {
			throw new ValidacaoException("Nome não pode ser nulo");
		}
		this.nome = nome.trim().toLowerCase();
	}
	
	public void setEmail(String email) {
		this.email = new Email(email);
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}