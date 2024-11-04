async function carregarDetalhesLivro(id) {
    try {
      const response = await fetch(`http://localhost:8080/livro/${id}`);
      const livro = await response.json();
      preencherTabela(livro);
      preencherDetalhes(livro);
      exibirImagemPrincipal(livro.imagemPrincipal);
      exibirPreco(livro.precificacao);
    } catch (error) {
      console.error('Erro ao carregar detalhes do livro:', error);
    }
  }
  
  function preencherTabela(livro) {
    const table = document.getElementById('livroDetails');
    const data = [
      { item: 'Editora',  informacao: `${livro.editora}` },
      { item: 'Páginas', informacao: `${livro.paginas} páginas` },
      { item: 'ISBN', informacao: livro.isbn },
      { item: 'Dimensões', informacao: `${livro.largura} x ${livro.altura} x ${livro.profundidade} cm` },
      { item: 'Peso', informacao: `${livro.peso} gramas` }
    ];
  
    data.forEach(({ item, informacao }) => {
      const row = table.insertRow();
      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);
      cell1.textContent = item;
      cell2.textContent = informacao;
    });
  }
  
  function preencherDetalhes(livro) {
    document.getElementById('categoria').textContent = livro.categorias;
    document.getElementById('titulo').textContent = livro.titulo;
    document.getElementById('autor').textContent = livro.autores;
    document.getElementById('sinopse').textContent = livro.sinopse;
    document.getElementById('preco').textContent = livro.preco.toFixed(2);
  }
  
  function exibirImagemPrincipal(urlImagem) {
    const imgElement = document.querySelector('.imagem-principal');
    imgElement.src = urlImagem;
  }
   
  function exibirPreco(preco) {
    document.getElementById('preco').textContent = `R$ ${preco.toFixed(2)}`;
  }
  
  const urlParams = new URLSearchParams(window.location.search);
  const idLivro = urlParams.get('id');
  
  document.addEventListener('DOMContentLoaded', () => {
    carregarDetalhesLivro(idLivro);
  
    const quantidadeElemento = document.getElementById('quantidade');
    let quantidade = parseInt(quantidadeElemento.textContent, 10);
  
    document.getElementById('incrementar').addEventListener('click', () => {
      quantidade += 1;
      quantidadeElemento.textContent = quantidade;
    });
  
    document.getElementById('decrementar').addEventListener('click', () => {
      if (quantidade > 0) {
        quantidade -= 1;
        quantidadeElemento.textContent = quantidade;
      }
    });
  });
  