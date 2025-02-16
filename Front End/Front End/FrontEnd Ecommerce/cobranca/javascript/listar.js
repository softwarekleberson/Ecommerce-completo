document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('user-list');

    async function carregarUsuarios() {
        const token = localStorage.getItem("token"); 

        if (!token) {
            alert("Erro: Token JWT não encontrado! Faça login novamente.");
            window.location.href = "login.html"; 
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/cliente/cobrancas", {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar endereços de cobrança: ' + response.statusText);
            }

            const data = await response.json();

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
        } catch (error) {
            console.error("Erro ao carregar cobranças:", error);
        }
    }

    carregarUsuarios();
});

async function excluirEntrega(idCobranca) {
    if (!confirm("Tem certeza que deseja excluir esta cobrança?")) {
        return;
    }

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/cliente/cobrancas/${idCobranca}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            alert("Este endereço de cobrança está sendo usado em um pedido.");
            throw new Error("Erro ao excluir cobrança: " + response.statusText);
        }

        alert("Cobrança excluída com sucesso!");
        location.reload(); 
    } catch (error) {
        console.error("Erro ao excluir cobrança:", error);
    }
}
