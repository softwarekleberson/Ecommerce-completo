package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Telefone;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.Roles;
import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Cliente")
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends UserEntity{

	public static final int VERICA_CPF = 11;
	private String cpf;
	private LocalDate nascimento;
	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Embedded
	private Telefone telefone;

	@OneToMany(mappedBy = "cliente")
	private List<Cartao> cartoes;

	@OneToMany(mappedBy = "cliente")
	private List<Entrega> entregas;

	@OneToMany(mappedBy = "cliente")
	private List<Cobranca> cobrancas;

	@OneToMany(mappedBy = "cliente")
	private List<Cupom> cupons;

	public Cliente(@Valid DadosCadastroCliente dados) {
		super(dados.nome(),dados.email(),dados.senha(),dados.confirmarSenha());
							
		setAtivo(true);
		setCpf(dados.cpf());
		setGenero(dados.genero());
		setNascimento(dados.nascimento());
		setTelefone(dados);
		setEntregas(dados.entrega());
		setCobrancas(dados.cobranca());
		setRole(Roles.CLIENTE);
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.trim().length() != VERICA_CPF) {
			throw new ValidacaoException("Cpf deve conter apenas numeros");
		}
		this.cpf = cpf.trim().toLowerCase();
	}

	public void setNascimento(LocalDate nacimento) {
		this.nascimento = nacimento;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setTelefone(DadosCadastroCliente dados) {
		this.telefone = new Telefone(dados);
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public void setEntregas(List<DadosCadastroEntrega> entregas) {
		this.entregas = entregas.stream()
				.map(entrega -> new Entrega(entrega)) 
				.collect(Collectors.toList());
	}

	public void setCobrancas(List<DadosCadastroCobranca> cobrancas) {
		this.cobrancas = cobrancas.stream()
				.map(cobranca -> new Cobranca(cobranca))
				.collect(Collectors.toList());
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String devolveSenhaCriptografada() {
		return this.getSenha();
	}
}