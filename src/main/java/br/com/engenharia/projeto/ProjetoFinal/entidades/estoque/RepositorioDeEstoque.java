package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

import java.util.Optional;

public interface RepositorioDeEstoque {

	void salvar(Estoque estoque);
	Optional<Estoque> verificaDisponibilidadeLivro(Long idLivro);
}
