document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('user-list');

    function getClienteIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('idCliente') || 1;
    }

    function carregarUsuarios(idCliente) {
        fetch(`http://localhost:8080/cartoes/${idCliente}`)
            .then(response => response.json())
            .then(data => {
                
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                 
                    userList.innerHTML = '';

                    data.content.forEach(cartao => {
                        const div = document.createElement('div');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h4 id="nomeImpresso">${cartao.nomeImpresso}</h4>
                            <p id="codigo ">Codigo : ${cartao.codigo}</p>
                            <div class="actions">
                                <a onclick="excluirCartao(${cartao.id})" href="#">Excluir</a>
                                <p>|</p>
                                <a href="alterar-cartao.html?cartaoId=${cartao.id}">Alterar</a>
                            </div>
                        `;
                        userList.appendChild(div);
                    });
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error('Erro ao carregar usuários:', error);
            });
    }

    const idCliente = getClienteIdFromURL();
    if (idCliente) {
        carregarUsuarios(idCliente);
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }
});

function excluirCartao(cartaoId) {
    if (confirm('Tem certeza que deseja excluir este cartão?')) {
        fetch(`http://localhost:8080/cartoes/${cartaoId}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                const idCliente = getClienteIdFromURL();
                if (idCliente) {
                    carregarUsuarios(idCliente);
                } else {
                    console.error('ID do cliente não encontrado na URL.');
                }
            } else {
                console.error('Erro ao excluir cartão:', response.statusText);
            }
        })
        .catch(error => {
            console.error('Erro ao excluir cartão:', error);
        });
    }
}