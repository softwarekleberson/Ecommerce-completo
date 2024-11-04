document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

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
    console.log(id)

    const data = {
        "principal": principal || null,
        "receptorCobranca": receptorCobranca || null,
        "tipoResidenciaCobranca": tipoResidenciaCobranca  || null,
        "tipoLogradouroCobranca": tipoLogradouroCobranca  || null,
        "logradouroCobranca": logradouroCobranca  || null,
        "numeroCobranca": numeroCobranca  || null,
        "bairroCobranca": bairroCobranca  || null,
        "cepCobranca": cepCobranca  || null,
        "observacaoCobranca": observacaoCobranca  || null,
        "cidadeCobranca": cidadeCobranca  || null,
        "estadoCobranca": estadoCobranca  || null,
        "paisCobranca": paisCobranca  || null,
    };


    const url = `http://localhost:8080/endereco/cobranca/${id}`; 
    sendDataToBackend(data, url);

    this.reset();
    window.location.href = "cobranca.html";
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('cobrancaId');
}

function sendDataToBackend(data, url) {
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error sending data to the backend');
        }
        return response.json();
    })
    .then(data => {
        console.log('Data sent successfully:', data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
