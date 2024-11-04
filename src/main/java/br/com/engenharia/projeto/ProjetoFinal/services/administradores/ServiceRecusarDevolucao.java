package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.RepositorioDeDevolucao;
import jakarta.validation.Valid;

@Service
public class ServiceRecusarDevolucao {

	@Autowired 
	private RepositorioDeDevolucao repositorioDeDevolucao;
	
	public DadosDetalhamentoTotalDevolucao devolucaoRecusada(@Valid DadosAtualizacaoDevolucao dados) {
		var recusaDevolucao = repositorioDeDevolucao.carregarDevolucao(dados.codigoDevolucao());
		
		recusaDevolucao.setDataConclusaoTroca(LocalDate.now());
		recusaDevolucao.devoluvaoChegou(dados.esperandoDevolucaoOuRecebido());
		recusaDevolucao.analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa.TROCA_RECUSADA);
		repositorioDeDevolucao.salvar(recusaDevolucao);
		
		return new DadosDetalhamentoTotalDevolucao(recusaDevolucao);
	}
}