package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Email;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato.Telefone;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Cliente")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public static final int VERIFICA_NOME = 2;
	private String nome;

	public static final int VERICA_CPF = 11;
	private String cpf;

	private LocalDate nascimento;
	private String senha;
	private Boolean ativo;

	@Transient
	private String confirmar_Senha;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Embedded
	private Telefone telefone;

	@Embedded
	private Email email;

	@OneToMany(mappedBy = "cliente")
	private List<Cartao> cartoes;

	@OneToMany(mappedBy = "cliente")
	private List<Entrega> entregas;

	@OneToMany(mappedBy = "cliente")
	private List<Cobranca> cobrancas;

	@OneToMany(mappedBy = "cliente")
	private List<Cupom> cupons;

	public Cliente(@Valid DadosCadastroCliente dados) {

		algoritmoVerificaSenha(dados.senha(), dados.confirmarSenha());
		algoritmoVerificaFormatoSenha(dados.senha());
		
		setAtivo(true);
		setNome(dados.nome());
		setCpf(dados.cpf());
		setGenero(dados.genero());
		setNascimento(dados.nascimento());
		setSenha(dados.senha());
		setConfirmar_Senha(dados.confirmarSenha());
		setTelefone(dados);
		setEmail(dados.email());
		setEntregas(dados.entrega());
		setCobrancas(dados.cobranca());
	}
	
	public void algoritmoVerificaSenha(String senha, String confirmaSenha) {
		AlgoritmoVerificaSenhaCliente.algoritmoVerificaSenhaCliente(senha, confirmaSenha);
	}
	
	public void algoritmoVerificaFormatoSenha(String senha) {
		AlgoritmoVerificaFormatoSenha.algoritmoVerificaFormatoSenha(senha);
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setNome(String nome) {
		if (nome == null || nome.trim().length() <= VERIFICA_NOME) {
			throw new ValidacaoException("Nome deve possuir mais de 2 digitos");
		}
		this.nome = nome.trim().toLowerCase();
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

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public void setConfirmar_Senha(DadosAtualizacaoSenha senha) {
		this.confirmar_Senha = senha.confirmarSenha();
	}

	public void CriptografarSenha(String senhaCriptografada) {
		this.senha = senhaCriptografada;
	}

	public String devolveSenhaCriptografada() {
		return this.senha;
	}

	public void setConfirmar_Senha(String confirmar_Senha) {
		this.confirmar_Senha = confirmar_Senha;
	}

	public void setEmail(String email) {
		this.email = new Email(email);
	}

	public void setEntregas(List<DadosCadastroEntrega> entregas) {
		this.entregas = entregas.stream()
				.map(entrega -> new Entrega(entrega)) // Convertendo DadosCadastroEntrega para Entrega
				.collect(Collectors.toList());
	}

	public void setCobrancas(List<DadosCadastroCobranca> cobrancas) {
		this.cobrancas = cobrancas.stream()
				.map(cobranca -> new Cobranca(cobranca)) // Convertendo DadosCadastroCobranca para Cobranca
				.collect(Collectors.toList());
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}