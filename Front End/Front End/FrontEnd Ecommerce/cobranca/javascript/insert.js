document.getElementById('myForm').addEventListener('submit', function(event) {
    var isChecked = document.getElementById('principal').checked;
    document.getElementById('principal').value = isChecked ? 'true' : 'false';
});

document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    var idCliente = document.getElementById('idCliente').value;
    var principal = document.getElementById('principal').checked;
    var receptorCobranca = document.getElementById('receptorCobranca').value
    var tipoResidenciaCobranca = document.getElementById('tipoResidenciaCobranca').value;
    var tipoLogradouroCobranca = document.getElementById('tipoLogradouroCobranca').value;
    var logradouroCobranca = document.getElementById('logradouroCobranca').value;
    var numeroCobranca = document.getElementById('numeroCobranca').value;
    var bairroCobranca = document.getElementById('bairroCobranca').value;
    var cepCobranca = document.getElementById('cepCobranca').value;
    var observacaoCobranca = document.getElementById('observacaoCobranca').value;
    var cidadeCobranca = document.getElementById('cidadeCobranca').value;
    var estadoCobranca = document.getElementById('estadoCobranca').value;
    var paisCobranca = document.getElementById('paisCobranca').value;
    
    var data = {
        "idCliente": idCliente,
        "principal": principal,
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

    sendDataToBackend(data);
    window.location.href = "cobranca.html"
});

function sendDataToBackend(data) {
    fetch('http://localhost:8080/endereco/cobranca', {
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
