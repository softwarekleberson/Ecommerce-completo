document.getElementById('form').addEventListener('submit', function (event) {
    event.preventDefault();

    const codigoPedido = document.getElementById('codigo').value;
    const token = localStorage.getItem('token'); 

    if (!token) {
        console.error("Token não encontrado!");
        return;
    }

    const dados = {
        codigo: codigoPedido
    };

    fetch('http://localhost:8080/admin/pedidos/status/entrega/transporte', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` 
        },
        body: JSON.stringify(dados)
    })
    .then(response => {
        const alertBox = document.getElementById('alert');
        if (response.ok) {
            alertBox.textContent = 'Status atualizado com sucesso!';
            alertBox.className = 'alert success';
        } else {
            throw new Error('Erro ao enviar o pedido');
        }
        alertBox.style.display = 'block';
    })
    .catch(error => {
        const alertBox = document.getElementById('alert');
        alertBox.textContent = 'Ocorreu um erro ao atualizar o status.';
        alertBox.className = 'alert error';
        alertBox.style.display = 'block';
        console.error('Erro:', error);
    });
});
