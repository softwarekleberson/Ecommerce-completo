package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;

@Service
public class ValidaExistenciaCliente implements IStrategyCliente{

    private static final String MENSAGEM_ERRO = "Cpf cadastrado anteriormente";
	
	@Autowired
	private ClienteRepository repository;
	
	public ValidaExistenciaCliente(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public void processar(Cliente dominio) {
		
		Optional<Cliente> cliente = repository.findByCpf(dominio.getCpf());
		
		if(!cliente.isEmpty()) {
			throw new ValidacaoException(MENSAGEM_ERRO);
		}		
	}
}