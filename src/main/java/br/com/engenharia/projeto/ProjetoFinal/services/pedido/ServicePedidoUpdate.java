package br.com.engenharia.projeto.ProjetoFinal.services.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.item.RepositorioDeItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import jakarta.validation.Valid;

@Service
public class ServicePedidoUpdate {

	@Autowired
	private RepositorioDeItem repositorioDeItem;

	public DadosDetalhamentoItem atualizar(@Valid DadosAtualizacaoItem dados, Long id) {
		DadosDetalhamentoItem item = repositorioDeItem.upadate(id, dados);
		return item;
	}
}