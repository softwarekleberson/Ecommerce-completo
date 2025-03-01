document.getElementById('nova-entrega').addEventListener('submit', function (event) {
    event.preventDefault();

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Usuário não autenticado! Redirecionando para login...");
        window.location.href = "/login-cliente.html";
    }

    const data = {
        "principalEntrega": document.getElementById('principalEntrega').checked,
        "tipoResidenciaEntrega": document.getElementById('tipoResidenciaEntrega').value,
        "receptorEntrega": document.getElementById('receptorEntrega').value,
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

    sendEntregaToBackend(data);
});

function sendEntregaToBackend(data) {
    const url = `http://localhost:8080/cliente/entregas`;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem("token")}`
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar dados para o backend');
            }
            return response.json();
        })
        .then(data => {
            console.log('Dados enviados com sucesso:', data);
            alert("Endereço de entrega cadastrado com sucesso!");
            document.getElementById('nova-entrega').reset();

            setTimeout(() => {
                location.reload();
            }, 2000);

        })
        .catch(error => {
            console.error('Erro:', error);
            alert("Erro ao cadastrar o endereço de entrega. Tente novamente.");
        });
}
