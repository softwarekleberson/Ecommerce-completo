package br.com.engenharia.projeto.ProjetoFinal.dao.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItensPagos;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface RepositorioDeItem {

	public void salvar(Item item);
	public DadosDetalhamentoItem upadate(Long id, DadosAtualizacaoItem dados);
	public void deletar(Long id);
	public Page<DadosDetalhamentoItem> listarItensDoCliente(Long clienteId, Pageable pageable);
	public Page<DadosDetalhamentoItensPagos> pedidosPagos(Long clienteId, Pageable pageable);
	public Page<DadosDetalhamentoItensPagos> listarTodosOsPedidos(Pageable pageable);

}
