package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.livro.IstrategyLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.RepositorioDeLivro;
import jakarta.validation.Valid;

@Service
public class ServiceInsertLivro {
	
	@Autowired
    private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private List<IstrategyLivro> validacoes;
	
    public DadosDetalhamentoLivro criar(@Valid DadosCadastroLivro dados) {

    	validacoes.forEach(v -> v.validar(dados));
        Livro livro = new Livro(dados);
        System.out.println(livro.getPrecificacao() + "service livro");
        
        repositorioDeLivro.salvar(livro);

        List<Categoria> categorias = criarCategoria(dados);
        List<Autor> autores = criarAutor(dados);
        List<Imagens> imagens = criarImagem(dados, livro);
        
        return new DadosDetalhamentoLivro(livro);
    }
    
    private List<Imagens> criarImagem(@Valid DadosCadastroLivro dados, Livro livro) {
        return dados.imagem().stream()
                .map(dadosImagem -> {
                    Imagens imagem = new Imagens(dadosImagem);
                    imagem.setLivro(livro); 
                    return imagem;
                })
                .collect(Collectors.toList());
    }

    private List<Categoria> criarCategoria(@Valid DadosCadastroLivro dados) {
        return dados.categoria().stream()
                .map(Categoria::new)
                .collect(Collectors.toList());
    }
    
    private List<Autor> criarAutor(@Valid DadosCadastroLivro dados) {
        return dados.autor().stream()
                .map(Autor::new)
                .collect(Collectors.toList());
    }
}