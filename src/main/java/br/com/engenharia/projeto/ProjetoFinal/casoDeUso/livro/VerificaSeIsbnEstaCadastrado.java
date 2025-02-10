package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.livro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;

@Service
public class VerificaSeIsbnEstaCadastrado implements IstrategyLivro{

	@Autowired
	private RepositorioDeLivro repositorioDeLivro;
	
    private static final String MENSAGEM_ERRO = "Este livro foi cadastrado anteriormente.";
	
	@Override
	public void validar(DadosCadastroLivro dados) {
		Optional<Livro> livro = repositorioDeLivro.isbnCadastradoAnteriormente(dados.isbn());
		if(livro.isPresent()) {
			throw new ValidacaoIsbnException(MENSAGEM_ERRO);
		}
	}
}
