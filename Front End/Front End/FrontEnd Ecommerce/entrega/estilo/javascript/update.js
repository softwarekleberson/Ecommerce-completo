document.getElementById("myForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; // Redireciona para login
        return;
    }

    const principal = document.getElementById('principal').checked;
    const receptorEntrega = document.getElementById('receptorEntrega').value;
    const tipoResidenciaEntrega = document.getElementById('tipoResidenciaEntrega').value;
    const tipoLogradouroEntrega = document.getElementById('tipoLogradouroEntrega').value;
    const logradouroEntrega = document.getElementById('logradouroEntrega').value;
    const numeroEntrega = document.getElementById('numeroEntrega').value;
    const bairroEntrega = document.getElementById('bairroEntrega').value;
    const cepEntrega = document.getElementById('cepEntrega').value;
    const observacaoEntrega = document.getElementById('observacaoEntrega').value;
    const cidadeEntrega = document.getElementById('cidadeEntrega').value;
    const estadoEntrega = document.getElementById('estadoEntrega').value;
    const paisEntrega = document.getElementById('paisEntrega').value;
    const fraseEntregaEntrega = document.getElementById('fraseEntregaEntrega').value;

    const id = obterIdDaURL();
    console.log(id);

    const data = {
        "principal": principal || null,
        "receptorEntrega": receptorEntrega || null,
        "tipoResidenciaEntrega": tipoResidenciaEntrega || null,
        "tipoLogradouroEntrega": tipoLogradouroEntrega || null,
        "logradouroEntrega": logradouroEntrega || null,
        "numeroEntrega": numeroEntrega || null,
        "bairroEntrega": bairroEntrega || null,
        "cepEntrega": cepEntrega || null,
        "observacaoEntrega": observacaoEntrega || null,
        "cidadeEntrega": cidadeEntrega || null,
        "estadoEntrega": estadoEntrega || null,
        "paisEntrega": paisEntrega || null,
        "fraseEntregaEntrega": fraseEntregaEntrega || null
    };

    const url = `http://localhost:8080/cliente/entregas/${id}`;
    
    try {
        await sendDataToBackend(data, url, token);
        this.reset();
        window.location.href = "entrega.html";
    } catch (error) {
        console.error("Erro ao enviar os dados:", error);
    }
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('entregaId');
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

        console.log('Dados enviados com sucesso:', await response.json());
    } catch (error) {
        console.error('Erro:', error);
        throw error;
    }
}
