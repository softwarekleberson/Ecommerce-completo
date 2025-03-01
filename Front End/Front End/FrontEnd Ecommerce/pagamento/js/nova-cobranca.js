document.getElementById('nova-cobranca').addEventListener('submit', async function(event) {
    event.preventDefault();

    const data = {
        principalCobranca: document.getElementById('principalCobranca').checked,
        receptorCobranca: sanitizeInput(document.getElementById('receptorCobranca').value),
        tipoResidenciaCobranca: sanitizeInput(document.getElementById('tipoResidenciaCobranca').value),
        tipoLogradouroCobranca: sanitizeInput(document.getElementById('tipoLogradouroCobranca').value),
        logradouroCobranca: sanitizeInput(document.getElementById('logradouroCobranca').value),
        numeroCobranca: sanitizeInput(document.getElementById('numeroCobranca').value),
        bairroCobranca: sanitizeInput(document.getElementById('bairroCobranca').value),
        cepCobranca: sanitizeInput(document.getElementById('cepCobranca').value),
        observacaoCobranca: sanitizeInput(document.getElementById('observacaoCobranca').value),
        cidadeCobranca: sanitizeInput(document.getElementById('cidadeCobranca').value),
        estadoCobranca: sanitizeInput(document.getElementById('estadoCobranca').value),
        paisCobranca: sanitizeInput(document.getElementById('paisCobranca').value)
    };

    console.log("Dados enviados:", data);
    await sendCobrancaToBackend(data);
});

async function sendCobrancaToBackend(data) {
    const url = "http://localhost:8080/cliente/cobrancas";
    const token = localStorage.getItem('token');

    if (!token) {
        alert("Usuário não autenticado! Redirecionando para login...");
        window.location.href = "/login-cliente.html";
    }

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data),
        });

        const responseText = await response.text();
        console.log("Resposta bruta do servidor:", responseText);

        const responseData = responseText ? JSON.parse(responseText) : {};
        
        if (!response.ok) {
            throw new Error(responseData.message || "Erro ao enviar dados para o backend.");
        }

        console.log("Dados enviados com sucesso:", responseData);
        alert("Cobrança cadastrada com sucesso!");
        document.getElementById('nova-cobranca').reset();
    } catch (error) {
        console.error("Erro ao processar a requisição:", error);
        alert(error.message);
    }
}

function sanitizeInput(value) {
    return value ? value.replace(/["\\]/g, '') : ""; 
}
