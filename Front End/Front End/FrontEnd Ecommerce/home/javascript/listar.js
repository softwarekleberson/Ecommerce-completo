const categoriaFiltrada = window.location.hash ? window.location.hash.substring(1).toLowerCase() : null;

fetch('http://localhost:8080/livros/listar')
    .then(response => response.json())
    .then(data => {
        const carroselLivros = document.getElementById('produtos');
        carroselLivros.classList.add('carrosel-livro');

        const livrosPorCategoria = {};
        data.content.forEach(livro => {
            const categoria = livro.categoria;
            if (!livrosPorCategoria[categoria]) {
                livrosPorCategoria[categoria] = [];
            }
            livrosPorCategoria[categoria].push(livro);
        });

        Object.keys(livrosPorCategoria).forEach(categoria => {
            const categoriaDiv = document.createElement('div');
            categoriaDiv.classList.add('secao-principal');
            categoriaDiv.innerHTML = `
                <div class="secao">
                    <div class="textos-carrosel">
                        <span class="titulo">${categoria.toUpperCase()}</span>
                    </div>
                        <span class="titulo">SEE ALL</span>
                </div>
            `;

            const linhaLivros = document.createElement('div');
            linhaLivros.classList.add('linha-livros');
            linhaLivros.style.display = 'flex';
            linhaLivros.style.flexWrap = 'nowrap';
            linhaLivros.style.overflowX = 'auto';

            livrosPorCategoria[categoria].forEach(livro => {
                const livroDiv = document.createElement('div');
                livroDiv.classList.add('livro');
                livroDiv.style.flex = '0 0 auto';

                livroDiv.innerHTML = `
                    <div class="livro-img">
                        <img class="img" src="${livro.primeiraImagem}" alt="${livro.titulo}" data-id="${livro.id}">
                    </div>
                    <div class="livro-span">
                        <span>${livro.titulo}</span>
                        <span class="livro-span-subtitulo">${livro.autor}</span>
                        <span>R$ ${livro.preco.toFixed(2)}</span>
                    </div>
                `;

                livroDiv.querySelector('.img').addEventListener('click', function () {
                    const livroId = livro.id;
                    window.location.href = `detalhes.html?id=${livroId}`;
                });

                linhaLivros.appendChild(livroDiv);
            });

            categoriaDiv.appendChild(linhaLivros);
            carroselLivros.appendChild(categoriaDiv);
        });
    })
    .catch(error => console.error('Ocorreu um erro:', error));
