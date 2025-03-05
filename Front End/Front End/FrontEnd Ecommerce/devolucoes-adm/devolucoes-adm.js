const apiUrl = `http://localhost:8080/admin/devolucoes`;

async function fetchDevolucoes() {
    try {
        const token = localStorage.getItem('token'); 
        if (!token) {
            console.error("Token não encontrado!");
            return;
        }

        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}` 
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar as devoluções');
        }

        const data = await response.json();
        populateTable(data.content);
    } catch (error) {
        console.error(error);
    }
}

function populateTable(devolucoes) {
    const devolucoesTable = document.getElementById('devolucoes');
    devolucoesTable.innerHTML = ''; 

    devolucoes.forEach(devolucao => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${devolucao.idDevolucao}</td>
            <td>${devolucao.codigoDevolucao}</td>
            <td>${devolucao.codigoPedido}</td>
            <td>${devolucao.dataPedidoTroca}</td>
            <td>${devolucao.dataConclusaoTroca ? devolucao.dataConclusaoTroca : 'Não Concluído'}</td>
            <td>${devolucao.emailAdm}</td>
            <td>${devolucao.analisePedidoDevolucao}</td>
            <td>${devolucao.analiseDevolucao}</td>
        `;
        devolucoesTable.appendChild(row);
    });
}

fetchDevolucoes();
