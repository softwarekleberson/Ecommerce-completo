package br.com.engenharia.projeto.ProjetoFinal.entidades.pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;

public interface RepositorioDePedido {

	public void salvar(Pedido pedido);
	public Optional<Pedido> verificaCodigoPedido(String codigoPedido);
	Page<DadosDetalhamentoPedido> listarPedidosCliente(Long clienteId, Pageable pageable);
	public Pedido devolvePedidoPeloCodigo(String codigoPedido);
	public List<Pedido> encontrarPedidosNaoPagosAposDataLimite(LocalDate dataLimite);
	public void excluir(Pedido pedido);
	
}
