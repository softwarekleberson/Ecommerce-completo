document.getElementById('myForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    const data = {
        "principalEntrega": document.getElementById('principalEntrega').checked,
        "receptorEntrega": document.getElementById('receptorEntrega').value,
        "tipoResidenciaEntrega": document.getElementById('tipoResidenciaEntrega').value,
        "tipoLogradouroEntrega": document.getElementById('tipoLogradouroEntrega').value,
        "logradouroEntrega": document.getElementById('logradouroEntrega').value,
        "numeroEntrega": document.getElementById('numeroEntrega').value,
        "bairroEntrega": document.getElementById('bairroEntrega').value,
        "cepEntrega": document.getElementById('cepEntrega').value,
        "observacaoEntrega": document.getElementById('observacaoEntrega').value,
        "cidadeEntrega": document.getElementById('cidadeEntrega').value,
        "estadoEntrega": document.getElementById('estadoEntrega').value,
        "paisEntrega": document.getElementById('paisEntrega').value,
        "fraseEntregaEntrega": document.getElementById('fraseEntregaEntrega').value
    };

    try {
        await sendDataToBackend(data, token);
        window.location.href = "entrega.html";
    } catch (error) {
        console.error('Erro ao enviar dados:', error);
    }
});

async function sendDataToBackend(data, token) {
    try {
        const response = await fetch(`http://localhost:8080/cliente/entregas`, {
            method: 'POST',
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
        document.getElementById('myForm').reset();

    } catch (error) {
        console.error('Erro:', error);
    }
}
