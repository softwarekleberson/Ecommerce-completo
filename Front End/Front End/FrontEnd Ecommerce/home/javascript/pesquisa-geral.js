document.getElementById('pesquisa').addEventListener('click', function (event) {
    event.preventDefault(); 

    const query = document.getElementById('searchQuery').value;

    if (query.trim()) {

        fetch('http://localhost:8080/livro/consulta/pesquisa', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify({ termoDePesquisa: query }) 
        })
        .then(response => response.json()) 
        .then(data => {
            const produtosDiv = document.getElementById('produtos'); // Seleciona a div onde os produtos serão exibidos
            produtosDiv.innerHTML = ''; 

            if (data.content.length > 0) {
                data.content.forEach(livro => {

                    const livroDiv = document.createElement('div');
                    livroDiv.classList.add('livro-item');
                    
                    livroDiv.innerHTML = `
                        <a href="detalhe-produto.html?id=${livro.id}">
                        <img src="${livro.imagemPrincipal}" alt="${livro.titulo}" />
                        <h2>${livro.titulo}</h2>
                        <p><strong>Sinopse:</strong> ${livro.sinopse}</p>
                        <p><strong>Autor:</strong> ${livro.autores}</p>
                        <p><strong>Páginas:</strong> ${livro.paginas}</p>
                        <p><strong>Preço:</strong> R$${livro.preco.toFixed(2)}</p>
                        </a>
                    `;

                    produtosDiv.appendChild(livroDiv);
                });
            } else {
                produtosDiv.innerHTML = '<p>Nenhum livro encontrado para esta pesquisa.</p>';
            }
        })
        .catch(error => {
            console.error('Erro ao realizar a pesquisa:', error);
            alert('Ocorreu um erro ao realizar a pesquisa. Tente novamente mais tarde.');
        });
    } else {
        alert('Por favor, insira um termo de busca.');
    }
});
