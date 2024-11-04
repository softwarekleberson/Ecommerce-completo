package br.com.engenharia.projeto.ProjetoFinal.entidades.endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosAtualizacaoCobrancas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosDetalhamentoCobranca;

public interface RepositorioDeCobranca {

	public void salvar(Cobranca cobranca);
	Page<DadosDetalhamentoCobranca> listarEnderecosCobrancaDoCliente(Long clienteId, Pageable pageable);
	void excluir(Long idCobranca);
	DadosDetalhamentoCobranca salvarNovaCobranca(Cobranca cobranca);
	Cobranca alterar(Long cobrancaId, DadosAtualizacaoCobrancas dados);
}
