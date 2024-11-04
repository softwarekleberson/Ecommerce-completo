document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const principal = document.getElementById('principal').checked;
    const receptorEntrega = document.getElementById('receptorEntrega').value;
    const tipoResidenciaEntrega = document.getElementById('tipoResidenciaEntrega').value;
    const tipoLogradouroEntrega = document.getElementById('tipoLogradouroEntrega').value;
    const logradouroEntrega = document.getElementById('logradouroEntrega').value;
    const numeroEntrega = document.getElementById('numeroEntrega').value;
    const bairroEntrega = document.getElementById('bairroEntrega').value;
    const cepEntrega = document.getElementById('cepEntrega').value;
    const observacaoEntrega = document.getElementById('observacaoEntrega').value;
    const cidadeEntrega = document.getElementById('cidadeEntrega').value;
    const estadoEntrega = document.getElementById('estadoEntrega').value;
    const paisEntrega = document.getElementById('paisEntrega').value;
    const fraseEntregaEntrega = document.getElementById('fraseEntregaEntrega').value;
    
    const id = obterIdDaURL(); 
    console.log(id)

    const data = {
        "principal": principal || null,
        "receptorEntrega": receptorEntrega || null,
        "tipoResidenciaEntrega": tipoResidenciaEntrega  || null,
        "tipoLogradouroEntrega": tipoLogradouroEntrega  || null,
        "logradouroEntrega": logradouroEntrega  || null,
        "numeroEntrega": numeroEntrega  || null,
        "bairroEntrega": bairroEntrega  || null,
        "cepEntrega": cepEntrega  || null,
        "observacaoEntrega": observacaoEntrega  || null,
        "cidadeEntrega": cidadeEntrega  || null,
        "estadoEntrega": estadoEntrega  || null,
        "paisEntrega": paisEntrega  || null,
        "fraseEntregaEntrega": fraseEntregaEntrega  || null
    };


    const url = `http://localhost:8080/endereco/entrega/${id}`; 
    sendDataToBackend(data, url);

    this.reset();
   window.location.href = "entrega.html";
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('entregaId');
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
