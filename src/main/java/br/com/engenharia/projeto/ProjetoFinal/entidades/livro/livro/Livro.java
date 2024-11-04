package br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroAutor;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroCategoria;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroDimensao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroImagem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.imagem.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.precificacao.Precificacao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Livro")
@Table(name = "livros")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ativo;
    
    private BigDecimal preco;
    
    private LocalDate data;

    public static final int QUANTIDADE_MAXIMA_CARACTERES_TITULO = 100;
    private String titulo;
    private String isbn;
    private int paginas;
    private String sinopse;
    
    @Column(name = "codigo_barra")
    private String codigoBarra;

    @Embedded
    private Dimensoes dimensoes;

    @Embedded
    private Editora editora;

    @Embedded
    private Edicao edicao;
    
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estoque> estoques;
    
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Imagens> imagens;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "livro_autor",
        joinColumns = { @JoinColumn(name = "livro_id") },
        inverseJoinColumns = { @JoinColumn(name = "autor_id") }
    )
    private List<Autor> autores;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "livro_categoria",
        joinColumns = { @JoinColumn(name = "livro_id") },
        inverseJoinColumns = { @JoinColumn(name = "categoria_id") }
    )
    private List<Categoria> categorias;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precificacao_id")
    private Precificacao precificacao;
    
    public Livro(DadosCadastroLivro dados) {
        setAtivo(false);
        setData(dados.data());
        setPreco(dados.preco());
        setTitulo(dados.titulo());
        setIsbn(dados.isbn());
        setPaginas(dados.paginas());
        setSinopse(dados.sinopse());
        setCodigoBarra(dados.codigoBarra());
        setDimensoes(dados.dimensoes());
        setEditora(dados.editora());
        setEdicao(dados.edicao());
        setImagem(dados.imagem());
        setAutores(dados.autor());
        setCategorias(dados.categoria());
        setPrecificacao(dados.idPrecificacao());
    }
    
    public void setPrecificacao(Long idPrecificacao) {
		this.precificacao = new Precificacao();
		precificacao.setId(idPrecificacao);
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public void setPreco(BigDecimal preco) {
        if(preco.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new ValidacaoException("Preço não deve ser menor ou igual a 0");
        }
        this.preco = preco;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty() || titulo.length() > QUANTIDADE_MAXIMA_CARACTERES_TITULO) {
            throw new ValidacaoException("Quantidade de caracteres em título superior ao permitido");
        }
        this.titulo = titulo.trim().toLowerCase();
    }

    public void setIsbn(String isbn) {
        String regex = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$";
        if (!isbn.matches(regex)) {
            throw new ValidacaoException("Isbn incorreto, formato correto xxx-x-xx-xxxxxx-x");
        }
        this.isbn = isbn.trim().toLowerCase();
    }

    public void setPaginas(int paginas) {
        if (paginas <= 0) {
            throw new ValidacaoException("Quantidade de páginas deve ser superior a 0 páginas");
        }
        this.paginas = paginas;
    }

    public void setSinopse(String sinopse) {
        if (sinopse == null || sinopse.trim().isEmpty()) {
            throw new ValidacaoException("Sinopse não deve ser nula ou vazia");
        }
        this.sinopse = sinopse.trim().toLowerCase();
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra.trim().toLowerCase();
    }
    
    public void setDimensoes(DadosCadastroDimensao dados) {
        this.dimensoes = new Dimensoes(dados);
    }

    public void setEditora(String editora) {
        this.editora = new Editora(editora);
    }
   
    public void setEdicao(String edicao) {
        this.edicao = new Edicao(edicao);
    }

    public void setImagem(List<DadosCadastroImagem> imagens) {
        this.imagens = imagens.stream()
            .map(dados -> {
                Imagens imagem = new Imagens(dados);
                imagem.setLivro(this);
                return imagem;
            })
            .collect(Collectors.toList());
    }
    
    public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

    public void setAutores(List<DadosCadastroAutor> autores) {
        this.autores = autores.stream()
                .map(Autor::new)
                .collect(Collectors.toList());
    }

    public void setCategorias(List<DadosCadastroCategoria> categorias) {
        this.categorias = categorias.stream()
                .map(Categoria::new)
                .collect(Collectors.toList());
    }
}