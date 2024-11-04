package br.com.engenharia.projeto.ProjetoFinal.services.devolucao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao.IstrategyDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.RepositorioDeAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.entidades.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.validation.Valid;

@Service
public class ServiceGerarPedidoDevolucao {

	@Autowired
	private RepositorioDeAdministrador repositorioDeAdministrador;
	
	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
	@Autowired
	private RepositorioDeCliente repositorioDeCliente;
	
	@Autowired
	private List<IstrategyDevolucao> validacoes;
	
	@Autowired
	private DevolucaoDao devolucaoDao;
	
	@Autowired
	private RepositorioDeLog repositorioDeLog;
		
	public DadosDetalhamentoDevolucao pedidoDevolucao(@Valid DadosCadastroDevolucao dados, Long idCliente) {
		
		var pedido = carregaPedidoPeloCodigoPedido(dados);
		pedido.devolucaoPedida(DevolucaoFoiPedidaOUNAO.DEVOLUCAO_PEDIDO);
		
		if(pedido.getStatusPedido() != StatusPedido.PAGO) {
			throw new ValidacaoException("Pedido ainda não pago");
		}
		
		if(pedido.getStatusEntrega() != StatusEntrega.ENTREGUE) {
			throw new ValidacaoException("Pedido ainda não entregue");
		}
		
		repositorioDePedido.salvar(pedido);

		validacoes.forEach(v ->v.processar(dados));
		
		var cliente = carregaClientePeloId(idCliente);
		var admAleatorio = escolheAdmAleatoriamente();
		String codigoPedido = pedido.getCodigoPedido();
		String criaCodigoDevolucao = UUID.randomUUID().toString();
				
		var devolucao = new Devolucao(null, criaCodigoDevolucao,LocalDate.now(),
									  null, cliente, pedido, codigoPedido,
									  admAleatorio, EsperandoDevolucaoOuRecebido.ESPERANDO_DEVOLUCAO,
									  AnalisePedidoDevolucaoAceitoOuRecusa.EM_ANALISE
									  );
		
		devolucaoDao.salvar(devolucao);
		
		Log log = new Log(devolucao.getCliente().getId());
		repositorioDeLog.save(log);
		
		return new DadosDetalhamentoDevolucao(devolucao);
	}

	private Cliente carregaClientePeloId(Long id) {
		var cliente = repositorioDeCliente.recuperaClientePelo(id);
		return cliente;
	}

	private Administrador escolheAdmAleatoriamente() {
		var adm = repositorioDeAdministrador.pegaAdministradorAleatorio();
		return adm;
	}

	private Pedido carregaPedidoPeloCodigoPedido(DadosCadastroDevolucao dados) {
		var pedido = repositorioDePedido.devolvePedidoPeloCodigo(dados.codigoPedido());
		return pedido;
	}
}