const form = document.getElementById('excluirPedidosForm');

form.addEventListener('submit', async function (event) {
    event.preventDefault();

    const dias = document.getElementById('dias').value;
    const token = localStorage.getItem('token'); 

    if (!token) {
        console.error("Token não encontrado!");
        alert("Erro: usuário não autenticado.");
        return;
    }

    const dados = {
        dias: parseInt(dias)
    };

    try {
        const response = await fetch('http://localhost:8080/admin/pedidos/excluir/pedidos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` 
            },
            body: JSON.stringify(dados)
        });

        if (response.ok) {
            alert('Todos os pedidos serão excluídos em ' + dias + " dias");
        } else {
            alert('Erro ao excluir pedidos.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao enviar a requisição.');
    }
});
