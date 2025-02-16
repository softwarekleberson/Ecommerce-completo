document.getElementById("myForm").addEventListener("submit", async function(event) {
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
        "principal": principal || null,
        "receptorCobranca": receptorCobranca || null,
        "tipoResidenciaCobranca": tipoResidenciaCobranca || null,
        "tipoLogradouroCobranca": tipoLogradouroCobranca || null,
        "logradouroCobranca": logradouroCobranca || null,
        "numeroCobranca": numeroCobranca || null,
        "bairroCobranca": bairroCobranca || null,
        "cepCobranca": cepCobranca || null,
        "observacaoCobranca": observacaoCobranca || null,
        "cidadeCobranca": cidadeCobranca || null,
        "estadoCobranca": estadoCobranca || null,
        "paisCobranca": paisCobranca || null,
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
