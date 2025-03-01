package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.EstoqueRepository;

@Service
public class ServiceBaixaEstoque {

	public static void baixaNoEstoque(List<Pedido> pedidos, EstoqueRepository estoqueRepository) {
		System.out.println("Baixa estoque");
		 for (Pedido pedido : pedidos) {
		        for (Item item : pedido.getItens()) {
		            Long idDoLivro = item.getLivro().getId();
		            int quantidadeRequeridaNoPedido = item.getQuantidade();
		            
		            List<Estoque> estoques = estoqueRepository.findByLivroId(idDoLivro);
		            
		            for (Estoque estoque : estoques) {
		                if (quantidadeRequeridaNoPedido <= 0) {
		                    break; 
		                }
		                
		                if (estoque.getQuantidade() <= quantidadeRequeridaNoPedido) {

		                	quantidadeRequeridaNoPedido -= estoque.getQuantidade();
		                    estoque.setQuantidade(0); 
		                } else {

		                	estoque.setQuantidade(estoque.getQuantidade() - quantidadeRequeridaNoPedido);
		                    quantidadeRequeridaNoPedido = 0; 
		                }
		                
		                estoqueRepository.save(estoque); 
		            }

		            if (quantidadeRequeridaNoPedido > 0) {
		                throw new ValidacaoPagamentoServiceException("Estoque insuficiente para o livro ID: " + idDoLivro);
		            }
		        }
		    }	    
		    
		    verificaSeLivroConstaEmEstoque(pedidos, estoqueRepository);
	}
	
	private static void verificaSeLivroConstaEmEstoque(List<Pedido> pedidos, EstoqueRepository estoqueRepository) {
		
		 for (Pedido pedido : pedidos) {
		        for (Item item : pedido.getItens()) {
		            Long idDoLivro = item.getLivro().getId();
		            
		            List<Estoque> estoques = estoqueRepository.findByLivroId(idDoLivro);
		            for(Estoque estoque : estoques) {
		            	int quantidade =+ estoque.getQuantidade();
		            	 if(quantidade == 0) {
		            		 estoque.getLivro().setAtivo(false);
		            	 }
		            }
		        }
		 }   
	}
}
