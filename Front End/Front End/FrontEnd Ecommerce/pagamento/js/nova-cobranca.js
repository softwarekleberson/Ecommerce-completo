document.getElementById('nova-cobranca').addEventListener('submit', function(event) {
    event.preventDefault();

    // Extrai o idCliente da URL ou define um valor padrão
    const url = new URL(window.location.href);
    let idCliente = url.pathname.split('/').pop();
    idCliente = isNaN(Number(idCliente)) ? 1 : Number(idCliente); // Se não for número, usa o padrão 1

    var principalCobranca = document.getElementById('principalCobranca').checked;
    var tipoResidenciaCobranca = document.getElementById('tipoResidenciaCobranca').value;
    var receptorCobranca = document.getElementById('receptorCobranca').value;
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

    sendCobrancaToBackend(idCliente, data);
});

function sendCobrancaToBackend(idCliente, data) {
    // Constroi a URL dinâmica incluindo o idCliente
    const url = `http://localhost:8080/endereco/cobranca/${idCliente}`;

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
        document.getElementById('nova-cobranca').reset();
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
