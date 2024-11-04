document.addEventListener('DOMContentLoaded', () => {

    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    const clienteId = urlParams.get('clienteId') || 1;

    if (!id || !clienteId) {
        return;
    }

    document.getElementById('adicionar-carrinho').addEventListener('click', () => {
        const quantidade = document.getElementById('quantidade').innerText; 

        const url = `http://localhost:8080/pedidos/${id}/${clienteId}`;

        const data = {
            quantidade: quantidade
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log('Sucesso:', data);
            })
            .catch((error) => {
                console.error('Erro:', error);
                alert('Erro ao adicionar o livro ao carrinho. Tente novamente.');
            });
    });
});
