package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;

public interface RepositorioDeCartao {

	public Cartao salvar(Cartao entidade);
	Cartao alterar(Long idCartao, DadosAtualizacaoCartao dados);
	public void deletar(Long id);
	public Optional<Cartao> cartaoCadastradoAnteriormente(String numeroCartao);
	public Page listarCartaosDoCliente(Long clienteId, Pageable pageable);
}
