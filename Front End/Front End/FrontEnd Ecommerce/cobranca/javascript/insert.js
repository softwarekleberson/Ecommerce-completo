function getIdClienteFromURL() {
    const pathSegments = window.location.pathname.split('/');
    const idCliente = pathSegments[pathSegments.length - 1];

    return isNaN(idCliente) ? '1' : idCliente;
}

document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const idCliente = getIdClienteFromURL();
    
    const principalCobranca = document.getElementById('principalCobranca').checked;
    const receptorCobranca = document.getElementById('receptorCobranca').value;
    const tipoResidenciaCobranca = document.getElementById('tipoResidenciaCobranca').value;
    const tipoLogradouroCobranca = document.getElementById('tipoLogradouroCobranca').value;
    const logradouroCobranca = document.getElementById('logradouroCobranca').value;
    const numeroCobranca = document.getElementById('numeroCobranca').value;
    const bairroCobranca = document.getElementById('bairroCobranca').value;
    const cepCobranca = document.getElementById('cepCobranca').value;
    const observacaoCobranca = document.getElementById('observacaoCobranca').value;
    const cidadeCobranca = document.getElementById('cidadeCobranca').value;
    const estadoCobranca = document.getElementById('estadoCobranca').value;
    const paisCobranca = document.getElementById('paisCobranca').value;
    
    const data = {
        "principalCobranca": principalCobranca,
        "receptorCobranca": receptorCobranca,
        "tipoResidenciaCobranca": tipoResidenciaCobranca,
        "tipoLogradouroCobranca": tipoLogradouroCobranca,
        "logradouroCobranca": logradouroCobranca,
        "numeroCobranca": numeroCobranca,
        "bairroCobranca": bairroCobranca,
        "cepCobranca": cepCobranca,
        "observacaoCobranca": observacaoCobranca,
        "cidadeCobranca": cidadeCobranca,
        "estadoCobranca": estadoCobranca,
        "paisCobranca": paisCobranca,
    };

    sendDataToBackend(idCliente, data);
    window.location.href = "cobranca.html";
});

function sendDataToBackend(idCliente, data) {
    fetch(`http://localhost:8080/endereco/cobranca/${idCliente}`, { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
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
        document.getElementById('myForm').reset();
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
