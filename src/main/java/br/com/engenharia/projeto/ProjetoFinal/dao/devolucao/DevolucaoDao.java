package br.com.engenharia.projeto.ProjetoFinal.dao.devolucao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.DevolucaoDaoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.RepositorioDeDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.devolucao.DevolucaoRepository;

@Service
public class DevolucaoDao implements RepositorioDeDevolucao{

	@Autowired
	private DevolucaoRepository devolucao;
	
	@Override
	public void salvar(Devolucao devolucao) {
		this.devolucao.save(devolucao);
	}

	@Override
	public Page<DadosDetalhamentoTotalDevolucao> listarTodasAsDevolucoes(Pageable pageable, Long admId) {
		Page<Devolucao> devolucoesPage = devolucao.findByAdministrador_Id(admId, pageable);
		if(devolucoesPage.isEmpty()) {
			throw new DevolucaoDaoExcecao("Id do administrador incorreto");
		}
		return devolucoesPage.map(DadosDetalhamentoTotalDevolucao::new);
	}

	@Override
	public Devolucao carregarDevolucao(String codigoDevolucao) {
		Optional<Devolucao> devolucaoExiste = devolucao.findByCodigoDevolucao(codigoDevolucao);
		if(devolucaoExiste.isEmpty()) {
			throw new DevolucaoDaoExcecao("Codigo devolução incorreto ou não existe");
		}
		return devolucaoExiste.get();
	}

	@Override
	public Optional<Devolucao> verificaCodigoPedido(String codigoPedido) {
		Optional<Devolucao> devolucoes = devolucao.findByCodigoPedido(codigoPedido);
		return devolucoes;
	}
}