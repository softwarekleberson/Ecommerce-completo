document.addEventListener("DOMContentLoaded", async () => {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    const id = obterIdDaURL(); 
    if (!id) {
        alert("Erro: ID da cobrança não encontrado.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/cliente/cobrancas/${id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar dados da cobrança');
        }

        const dados = await response.json();

        document.getElementById('principal').checked = dados.principal;
        document.getElementById('tipoResidenciaCobranca').value = dados.tipoResidencia;
        document.getElementById('receptorCobranca').value = dados.receptor;
        document.getElementById('tipoLogradouroCobranca').value = dados.tipoLogradouto;
        document.getElementById('logradouroCobranca').value = dados.logradouro;
        document.getElementById('numeroCobranca').value = dados.numero;
        document.getElementById('bairroCobranca').value = dados.bairro;
        document.getElementById('cepCobranca').value = dados.cep;
        document.getElementById('observacaoCobranca').value = dados.observacao;
        document.getElementById('cidadeCobranca').value = dados.cidade;
        document.getElementById('estadoCobranca').value = dados.estado;
        document.getElementById('paisCobranca').value = dados.pais;

    } catch (error) {
        console.error("Erro ao carregar dados da cobrança:", error);
    }
});

document.getElementById("form").addEventListener("submit", async function(event) {
    event.preventDefault(); 

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    const principal = document.getElementById('principal').checked;
    const tipoResidenciaCobranca = document.getElementById('tipoResidenciaCobranca').value;
    const receptorCobranca = document.getElementById('receptorCobranca').value;
    const tipoLogradouroCobranca = document.getElementById('tipoLogradouroCobranca').value;
    const logradouroCobranca = document.getElementById('logradouroCobranca').value;
    const numeroCobranca = document.getElementById('numeroCobranca').value;
    const bairroCobranca = document.getElementById('bairroCobranca').value;
    const cepCobranca = document.getElementById('cepCobranca').value;
    const observacaoCobranca = document.getElementById('observacaoCobranca').value;
    const cidadeCobranca = document.getElementById('cidadeCobranca').value;
    const estadoCobranca = document.getElementById('estadoCobranca').value;
    const paisCobranca = document.getElementById('paisCobranca').value;

    const id = obterIdDaURL(); 
    console.log(id);

    const data = {
        principal: principal,
        receptorCobranca: receptorCobranca,
        tipoResidenciaCobranca: tipoResidenciaCobranca,
        tipoLogradouroCobranca: tipoLogradouroCobranca,
        logradouroCobranca: logradouroCobranca,
        numeroCobranca: numeroCobranca,
        bairroCobranca: bairroCobranca,
        cepCobranca: cepCobranca,
        observacaoCobranca: observacaoCobranca,
        cidadeCobranca: cidadeCobranca,
        estadoCobranca: estadoCobranca,
        paisCobranca: paisCobranca,
    };

    const url = `http://localhost:8080/cliente/cobrancas/${id}`; 

    await sendDataToBackend(data, url, token);

    this.reset();
    window.location.href = "cobranca.html";
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('cobrancaId');
}

async function sendDataToBackend(data, url, token) {
    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` 
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error('Erro ao enviar dados para o backend');
        }

        const responseData = await response.json();
        console.log('Dados enviados com sucesso:', responseData);
    } catch (error) {
        console.error('Erro:', error);
    }
}
