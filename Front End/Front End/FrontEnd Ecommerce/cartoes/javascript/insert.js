function getIdClienteFromURL() {
    const pathSegments = window.location.pathname.split('/');
    const idCliente = pathSegments[pathSegments.length - 1];

    return isNaN(idCliente) ? '1' : idCliente;
}

document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    sendDataToBackend(jsonData);
    this.reset();
    window.location.href = "cartoes.html";
});

function sendDataToBackend(data) {
    const idCliente = getIdClienteFromURL();

    fetch(`http://localhost:8080/cartoes/${idCliente}`, { 
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
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
