package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.CriptografaSenhaCliente;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.IStrategySenhaAtualizadaCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosDetalhamentoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ServiceClienteUpdate {
	
	@Autowired
	private ClienteRepository clienteRepositoy;
	
	@Autowired
    private RepositorioDeCliente repositorioDeCliente;
    
    @Autowired
    private RepositorioDeLog repositorioDeLog;
    
    @Autowired
    private List<IStrategySenhaAtualizadaCliente> validadores;
   
    @Autowired
    private List<CriptografaSenhaCliente> validaoresCriptografiaSenha;

	public DadosDetalhamentoCliente atualizarCliente(@Valid DadosAtualizacaoCliente dados) {
		Optional<Cliente> cliente = clienteRepositoy.findById(dados.idCliente());
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Id incorreto");
		}
		
		repositorioDeCliente.alterarCliente(cliente.get().getId(), dados); 
		Log log = new Log(dados.idCliente());
		repositorioDeLog.save(log);
		return null;		
	}
	
	public DadosDetalhamentoCliente atualizarSenha(@Valid DadosAtualizacaoSenha dados) {
		Optional<Cliente> cliente = clienteRepositoy.findById(dados.idCliente());
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Id incorreto");
		}
		
		validadores.forEach(v-> v.processar(dados));
		validaoresCriptografiaSenha.forEach(v-> v.processar(cliente.get()));
		repositorioDeCliente.alterarSenha(dados.idCliente(), cliente.get().devolveSenhaCriptografada());
		
		Log log = new Log(dados.idCliente());
		repositorioDeLog.save(log);
		return null;		
	}
}