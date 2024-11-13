document.getElementById('nova-entrega').addEventListener('submit', function(event) {
    event.preventDefault();

    // Extrai o idCliente da URL ou define um valor padrão
    const url = new URL(window.location.href);
    let idCliente = url.pathname.split('/').pop();
    idCliente = isNaN(Number(idCliente)) ? 1 : Number(idCliente); // Se não for número, usa o padrão 1

    var principalEntrega = document.getElementById('principalEntrega').checked;
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
        "principalEntrega": principalEntrega,
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

    sendEntregaToBackend(idCliente, data);
});

function sendEntregaToBackend(idCliente, data) {
    // Constroi a URL dinâmica incluindo o idCliente
    const url = `http://localhost:8080/endereco/entrega/${idCliente}`;

    fetch(url, {
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
        document.getElementById('nova-entrega').reset();
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
