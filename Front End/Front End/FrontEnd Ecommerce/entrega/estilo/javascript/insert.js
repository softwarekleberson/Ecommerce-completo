document.getElementById('myForm').addEventListener('submit', function(event) {
    var isChecked = document.getElementById('principal').checked;
    document.getElementById('principal').value = isChecked ? 'true' : 'false';
});

document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    var idCliente = document.getElementById('idCliente').value;
    var principal = document.getElementById('principal').checked;
    var tipoResidenciaEntrega = document.getElementById('tipoResidenciaEntrega').value;
    var receptorEntrega = document.getElementById('receptorEntrega').value;
    var tipoLogradouroEntrega = document.getElementById('tipoLogradouroEntrega').value;
    var logradouroEntrega = document.getElementById('logradouroEntrega').value;
    var numeroEntrega = document.getElementById('numeroEntrega').value;
    var bairroEntrega = document.getElementById('bairroEntrega').value;
    var cepEntrega = document.getElementById('cepEntrega').value;
    var observacaoEntrega = document.getElementById('observacaoEntrega').value;
    var cidadeEntrega = document.getElementById('cidadeEntrega').value;
    var estadoEntrega = document.getElementById('estadoEntrega').value;
    var paisEntrega = document.getElementById('paisEntrega').value;
    var fraseEntregaEntrega = document.getElementById('fraseEntregaEntrega').value;
    
    var data = {
        "idCliente": idCliente,
        "principal": principal,
        "receptorEntrega": receptorEntrega,
        "tipoResidenciaEntrega": tipoResidenciaEntrega,
        "tipoLogradouroEntrega": tipoLogradouroEntrega,
        "logradouroEntrega": logradouroEntrega,
        "numeroEntrega": numeroEntrega,
        "bairroEntrega": bairroEntrega,
        "cepEntrega": cepEntrega,
        "observacaoEntrega": observacaoEntrega,
        "cidadeEntrega": cidadeEntrega,
        "estadoEntrega": estadoEntrega,
        "paisEntrega": paisEntrega,
        "fraseEntregaEntrega": fraseEntregaEntrega
    };

    sendDataToBackend(data);
    window.location.href = "entrega.html"
});

function sendDataToBackend(data) {
    fetch('http://localhost:8080/endereco/entrega', {
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
