document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('user-list');

    function getClienteIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('idCliente') || 1;
    }

    function carregarUsuarios(idCliente) {
        fetch(`http://localhost:8080/endereco/cobranca/${idCliente}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar usuários: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    userList.innerHTML = '';

                    data.content.forEach(endereco => {
                        const div = document.createElement('div');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h3 id="nome">${endereco.receptor}</h3>
                            <p id="logradouro"> ${endereco.logradouro}</p>
                            <p id="tipoResidencia"> ${endereco.tipoResidencia} - ${endereco.numero} ${endereco.observacao}</p>
                            <p id="estado"> ${endereco.cidade}, ${endereco.estado} ${endereco.cep}
                            <p id="pais"> ${endereco.pais}</p>
                            <div class="actions">
                                <a onclick="excluirEntrega(${endereco.id})" href="#">Excluir</a>
                                <p>|</p>
                                <a href="alterar-cobranca.html?cobrancaId=${endereco.id}">Alterar</a>
                            </div>
                        `;
                        userList.appendChild(div);
                    });

                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error(error);
            });

    }

    const idCliente = getClienteIdFromURL();
    if (idCliente) {
        carregarUsuarios(idCliente);
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }

});

function excluirEntrega(idCobranca) {
    if (confirm("Tem certeza que deseja excluir esta cobrança?")) {
        fetch(`http://localhost:8080/endereco/cobranca/${idCobranca}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                alert("Está endereço de cobrança está sendo usado em um pedido")
                throw new Error('Erro ao excluir cobrança: ' + response.statusText);
            }
            
            location.reload();
        })
        .catch(error => {
            console.error(error);
        });
    }
}
