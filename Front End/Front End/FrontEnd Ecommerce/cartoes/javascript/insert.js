document.getElementById("myForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const idCliente = getIdClienteFromURL();

    const principal = document.getElementById('principal').checked;
    const numeroCartao = document.getElementById('numeroCartao').value;
    const nomeImpresso = document.getElementById('nomeImpresso').value;
    const codigo = document.getElementById('codigo').value;
    const bandeira = document.getElementById('bandeira').value;
    const dataExpiracao = document.getElementById('dataExpiracao').value;

    const data = {
        principal: principal,
        numeroCartao: numeroCartao,
        nomeImpresso: nomeImpresso,
        codigo: codigo,
        bandeira: bandeira,
        dataExpiracao: dataExpiracao,
    };

    sendDataToBackend(data);
    this.reset();
    window.location.href = "cartoes.html";
});

function getIdClienteFromURL() {
    const pathSegments = window.location.pathname.split('/');
    const idCliente = pathSegments[pathSegments.length - 1];
    return isNaN(idCliente) ? '1' : idCliente;
}

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
