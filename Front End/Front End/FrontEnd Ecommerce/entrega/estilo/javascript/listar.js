document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('user-list');

    async function carregarUsuarios() {
        const token = localStorage.getItem("token");

        if (!token) {
            alert("Erro: Token JWT não encontrado! Faça login novamente.");
            window.location.href = "login-cliente.html";
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/cliente/entregas`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar entregas: ' + response.statusText);
            }

            const data = await response.json();

            // Limpa o conteúdo da lista antes de adicionar novos cards
            userList.innerHTML = '';

            // Adiciona o card fixo primeiro
            const cardFixo = document.createElement('div');
            cardFixo.classList.add('card');
            cardFixo.innerHTML = `
                <h2>Adicionar Entrega</h2>
                <div class="actions">
                    <a class="link" href="adicionar-Entrega.html">Adicionar</a>
                </div>
            `;
            userList.appendChild(cardFixo);

            // Verifica se há conteúdo válido
            if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                data.content.forEach(endereco => {
                    const div = document.createElement('div');
                    div.classList.add('card');
                    div.innerHTML = `
                        <h3 id="nome">${endereco.receptor}</h3>
                        <p id="logradouro">${endereco.logradouro}</p>
                        <p id="tipoResidencia">${endereco.tipoResidencia} - ${endereco.numero} ${endereco.observacao}</p>
                        <p id="estado">${endereco.cidade}, ${endereco.estado} ${endereco.cep}</p>
                        <p id="pais">${endereco.pais}</p> 
                        <div class="actions">
                            <a onclick="excluirEntrega(${endereco.id})" href="#">Excluir</a>
                            <p>|</p>
                            <a href="alterar-entrega.html?entregaId=${endereco.id}">Alterar</a>
                        </div>
                    `;
                    userList.appendChild(div);
                });
            } else {
                console.error('Os dados retornados não estão no formato esperado.');
            }

        } catch (error) {
            console.error("Erro ao carregar entregas:", error);
        }
    }

    carregarUsuarios();
});

async function excluirEntrega(idEntrega) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html";
        return;
    }

    if (confirm("Tem certeza que deseja excluir esta entrega?")) {
        try {
            const response = await fetch(`http://localhost:8080/cliente/entregas/${idEntrega}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                alert("Este endereço de entrega está sendo usado em um pedido.");
                throw new Error('Erro ao excluir entrega: ' + response.statusText);
            }

            alert("Entrega excluída com sucesso!");
            location.reload();
        } catch (error) {
            console.error("Erro ao excluir entrega:", error);
        }
    }
}
