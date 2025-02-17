document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    const botaoAdicionar = document.getElementById('adicionar-carrinho');
    if (!botaoAdicionar) {
        console.error("Erro: Botão 'Adicionar ao Carrinho' não encontrado.");
        return;
    }

    botaoAdicionar.addEventListener('click', async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            alert("Você precisa estar logado para adicionar itens ao carrinho.");
            return;
        }

        const quantidadeElement = document.getElementById('quantidade');
        if (!quantidadeElement) {
            console.error("Erro: Elemento de quantidade não encontrado.");
            return;
        }

        const quantidade = parseInt(quantidadeElement.innerText, 10);
        if (isNaN(quantidade) || quantidade <= 0) {
            alert("Selecione uma quantidade válida.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/cliente/pedidos/${id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ quantidade })
            });

            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }

            const data = await response.json();
            console.log('Sucesso:', data);
            alert("Item adicionado ao carrinho com sucesso!");

            setTimeout(() => {
                location.reload();
            }, 2000);

        } catch (error) {
            console.error('Erro ao adicionar o item:', error);
            alert("Erro ao adicionar o item ao carrinho. Tente novamente.");
        }
    });
});
