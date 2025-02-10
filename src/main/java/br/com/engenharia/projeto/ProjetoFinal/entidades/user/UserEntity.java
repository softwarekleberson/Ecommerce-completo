package br.com.engenharia.projeto.ProjetoFinal.entidades.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_entity")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String nome;
	protected String email;
	protected String senha;
	
	@Transient
	protected String confirmarSenha;
	
	@Enumerated(EnumType.STRING)
	protected Roles roles;

	public UserEntity(String nome, String email, String senha, String confirmarSenha) {
		
		algoritmoVerificaFormatoSenha(senha);
		algoritmoConfirmacaoSenha(senha, confirmarSenha);
		
		setNome(nome);
		setEmail(email);
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
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
			throw new ValidacaoNomeException("Nome não pode ser nulo");
		}
		this.nome = nome.trim().toLowerCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
		        throw new ValidacaoEmailException("E-mail inválido");
		}
		this.email = email;
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

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(() -> roles.name()); 
    }

	 @Override
	 public String getUsername() {
	    return email;
	 }

	 @Override
	 public boolean isAccountNonExpired() {
       return true;
	 }

     @Override
     public boolean isAccountNonLocked() {
    	return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	     return true;
	  }

	  @Override
	  public String getPassword() {
	     return senha;  
	  }
	    
	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
}
