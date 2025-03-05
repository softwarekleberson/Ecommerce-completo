async function fetchPedidos() {
    try {
        const token = localStorage.getItem('token'); // Pegando o token do localStorage
        if (!token) {
            console.error("Token não encontrado!");
            return;
        }

        const response = await fetch('http://localhost:8080/admin/pedidos/listar-pedidos', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        renderPedidos(data);
    } catch (error) {
        console.error('Houve um problema com a requisição:', error);
    }
}

function renderPedidos(data) {
    const tbody = document.querySelector('#pedidos-table tbody');
    const totalPedidos = document.getElementById('total-pedidos').querySelector('p');
    tbody.innerHTML = ''; 

    data.content.forEach(pedido => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${pedido.codigoPedido}</td>
            <td>${pedido.nome}</td>
            <td>${pedido.quantidade}</td>
            <td>${pedido.dataPedido}</td>
            <td class="status">${pedido.status}</td>
            <td>R$ ${pedido.precoUnitario.toFixed(2)}</td>
            <td>R$ ${pedido.subtotal.toFixed(2)}</td>
        `;
        tbody.appendChild(tr);
    });

    totalPedidos.textContent = `Total de Pedidos: ${data.totalElements}`;
}

window.onload = fetchPedidos;
