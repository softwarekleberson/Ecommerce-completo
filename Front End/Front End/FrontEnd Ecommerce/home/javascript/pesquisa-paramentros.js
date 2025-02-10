document.getElementById("pesquisa-livros-form").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita o comportamento padrão de envio do formulário

    const livroConsultaDto = {
        titulo: document.getElementById('titulo').value || null,
        precoMenor: document.getElementById('precoMenor').value ? parseFloat(document.getElementById('precoMenor').value) : null,
        precoMaior: document.getElementById('precoMaior').value ? parseFloat(document.getElementById('precoMaior').value) : null,
        dataMenor: document.getElementById('dataMenor').value || null,
        dataMaior: document.getElementById('dataMaior').value || null,
        isbn: document.getElementById('isbn').value || null,
        paginas: document.getElementById('paginas').value ? parseInt(document.getElementById('paginas').value) : null,
        editora: document.getElementById('editora').value || null,
        edicao: document.getElementById('edicao').value || null,
        autorIds: document.getElementById('autorIds').value ? document.getElementById('autorIds').value.split(',').map(id => parseInt(id.trim())) : [],
        categoriaIds: document.getElementById('categoriaIds').value ? document.getElementById('categoriaIds').value.split(',').map(id => parseInt(id.trim())) : [],
    };

    fetch("http://localhost:8080/livros/listar/consulta/parametros", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(livroConsultaDto),
    })

        .then(response => response.json())
        .then(data => {
            const produtosDiv = document.getElementById('produtos'); 
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
});
