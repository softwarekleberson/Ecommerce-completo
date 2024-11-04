// Captura o hash da URL, removendo o símbolo #, e converte para minúsculas
const categoriaFiltrada = window.location.hash ? window.location.hash.substring(1).toLowerCase() : null;

// Faz a requisição à API
fetch('http://localhost:8080/livro')
    .then(response => response.json())
    .then(data => {
        const produtosDiv = document.getElementById('produtos');

        const livrosFiltrados = categoriaFiltrada
            ? data.content.filter(livro => livro.categoria.toLowerCase() === categoriaFiltrada)
            : data.content;

        livrosFiltrados.forEach(livro => {
            const div = document.createElement('div');
            div.innerHTML = `
                <a href="detalhe-produto.html?id=${livro.id}">
                    <img src="${livro.primeiraImagem}" alt="${livro.titulo}">
                </a>
                <h3>${livro.titulo}</h3>
                <p>${livro.autor}</p>
                <p>R$ ${livro.preco.toFixed(2)}</p>
                <p id=${livro.categoria}></p>
            `;
            produtosDiv.appendChild(div);
        });
    })
    .catch(error => console.error('Ocorreu um erro:', error));
