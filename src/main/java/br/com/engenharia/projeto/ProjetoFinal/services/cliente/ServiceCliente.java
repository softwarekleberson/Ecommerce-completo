package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.CriptografaSenhaCliente;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente.IStrategyCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DetalharCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeCobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.RepositorioDeEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ServiceCliente {
	
    @Autowired
    private RepositorioDeCliente repositorioDeCliente;

    @Autowired
    private RepositorioDeEntrega repositorioDeEntrega;

    @Autowired
    private RepositorioDeCobranca repositorioDeCobranca;
    
    @Autowired
    private RepositorioDeLog repositorioDeLog;

    @Autowired
    private List<IStrategyCliente> validadores;

    @Autowired
    private CriptografaSenhaCliente criptografiaSenha;

    @Transactional
    public DetalharCliente criar(@Valid DadosCadastroCliente dados) {
        
    	try {
    		
			Cliente cliente = new Cliente(dados);
			validadores.forEach(v -> v.processar(cliente));
			
			criptografiaSenha.processar(cliente);
			repositorioDeCliente.salvar(cliente);

			List<Cobranca> cobrancas = criarCobrancas(dados, cliente);
			List<Entrega> entregas = criarEntregas(dados, cliente);
			atribuirIdCliente(cobrancas, entregas, cliente.getId());

			salvarCobrancas(cobrancas);
			salvarEntregas(entregas);
			
			Log log = new Log(cliente.getId());
			repositorioDeLog.save(log);
			
			return new DetalharCliente(cliente);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return null;
    }

    private List<Cobranca> criarCobrancas(DadosCadastroCliente dados, Cliente cliente) {        
    	return dados.cobranca().stream()
                .map(Cobranca::new)
                .collect(Collectors.toList());        
    }

    private List<Entrega> criarEntregas(DadosCadastroCliente dados, Cliente cliente) {
        return dados.entrega().stream()
                .map(Entrega::new)
                .collect(Collectors.toList());
    }

    private void atribuirIdCliente(List<Cobranca> cobrancas, List<Entrega> entregas, Long clienteId) {
    	cobrancas.forEach(c -> c.setCliente(clienteId));
        entregas.forEach(e -> e.setCliente(clienteId));
    }

    private void salvarCobrancas(List<Cobranca> cobrancas) {
    	cobrancas.forEach(repositorioDeCobranca::salvar);
    }

    private void salvarEntregas(List<Entrega> entregas) {
    	entregas.forEach(repositorioDeEntrega::salvar);
    }
}