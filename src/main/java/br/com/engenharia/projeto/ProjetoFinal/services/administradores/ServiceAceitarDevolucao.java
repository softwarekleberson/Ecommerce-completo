package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.RepositorioDeCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.TipoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.RepositorioDeDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.RepositorioDeEstoque;
import jakarta.validation.Valid;

@Service
public class ServiceAceitarDevolucao {
	
	@Autowired
	private RepositorioDeDevolucao repositorioDeDevolucao;
	
	@Autowired
	private RepositorioDeCupom repositorioDeCupom;
	
	@Autowired
	private RepositorioDeEstoque repositorioDeEstoque;
	
	public DadosDetalhamentoTotalDevolucao devolucaoAceita (@Valid DadosAtualizacaoDevolucao dados) {
		var aceitaDevolucao = repositorioDeDevolucao.carregarDevolucao(dados.codigoDevolucao());
		
		aceitaDevolucao.setDataConclusaoTroca(LocalDate.now());
		aceitaDevolucao.devoluvaoChegou(dados.esperandoDevolucaoOuRecebido());
		aceitaDevolucao.analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa.TROCA_ACEITA);
		
		geraCupomAposAprovarDevolucao(aceitaDevolucao);
		devolucaoVoltaParaEstoque(dados, aceitaDevolucao);
		repositorioDeDevolucao.salvar(aceitaDevolucao);
		
		return new DadosDetalhamentoTotalDevolucao(aceitaDevolucao);
	}

	private void devolucaoVoltaParaEstoque(DadosAtualizacaoDevolucao dados, Devolucao devolucao) {
		if(dados.produtoVoltaParaEstoque() == ProdutoVoltaParaEstoque.SIM) {
			var estoque = new Estoque();
			
			estoque.setId(null);
			estoque.setLivro(devolucao.getPedido().getItens().get(0).getLivro().getId());
			estoque.setQuantidade(devolucao.getPedido().getItens().get(0).getQuantidade());
			estoque.setValorCusto(BigDecimal.ZERO);
			estoque.setDataEntrada(LocalDate.now());
			estoque.setFornecedor("Devolução feita pelo cliente");
			estoque.setEstadoDoProduto(dados.estadoProduto());
			
			repositorioDeEstoque.salvar(estoque);
		}
	}

	private DadosDetalhamentoCupom geraCupomAposAprovarDevolucao(Devolucao aceitaDevolucao) {
		Long clienteId = aceitaDevolucao.getPedido().getCliente().getId();
		Cupom gerarCupom = new Cupom();
		
		gerarCupom.setTipoCupom(TipoCupom.TROCA);
		gerarCupom.setValor(aceitaDevolucao.getPedido().getItens().get(0).getSubtotal());
		gerarCupom.setStatus(true);
		gerarCupom.setCliente(clienteId);
		
		repositorioDeCupom.salvar(gerarCupom);
		return new DadosDetalhamentoCupom(gerarCupom);
	}
}