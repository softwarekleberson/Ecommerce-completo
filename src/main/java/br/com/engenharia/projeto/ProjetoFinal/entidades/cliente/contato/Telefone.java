package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.contato;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Telefone {

	public static final int VERIFICA_DDD = 2;
	private String ddd;
	
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipoTelefone;
	
	public Telefone(DadosCadastroCliente dados) {
		setDdd(dados.ddd());
		setTelefone(dados.telefone());
		setTipoTelefone(dados.tipo());
	}

	public void setDdd(String ddd) {
		String regexDDD = "\\d{2}";
		if(!ddd.matches(regexDDD)) {
			throw new ValidacaoException("Ddd deve posusir 2 digitos");
		}
		this.ddd = ddd.trim().toLowerCase();
	}
	
	public void setTelefone(String telefone) {
        String regexTelefone = "\\d{8,9}";
        if(!telefone.matches(regexTelefone)) {
        	throw new ValidacaoException("Telefone deve conter 8 ou 9 digitos");
        }
		this.telefone = telefone.trim().toLowerCase();
	}
	
	public void setTelefone(DadosAtualizacaoCliente dadosAtualizacaoCliente) {
        String regexTelefone = "\\d{8,9}";
        if(!dadosAtualizacaoCliente.telefone().matches(regexTelefone)) {
        	throw new ValidacaoException("Telefone deve conter 8 ou 9 digitos");
        }
		this.telefone = dadosAtualizacaoCliente.telefone();
	}
	
	public void setTipoTelefone(TipoTelefone tipo) {
		this.tipoTelefone = tipo;
	}
	
	public void setTipoTelefone(DadosAtualizacaoCliente dadosAtualizacaoCliente) {
		this.tipoTelefone = dadosAtualizacaoCliente.tipo();
	}
}