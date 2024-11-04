package br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;

public interface RepositorioDeDevolucao {

	public void salvar(Devolucao devolucao);
	public Devolucao carregarDevolucao(String codigoDevolucao);
	public Page<DadosDetalhamentoTotalDevolucao> listarTodasAsDevolucoes(Pageable pageable, Long admId);
	public Optional<Devolucao> verificaCodigoPedido(String codigoPedido);

}
