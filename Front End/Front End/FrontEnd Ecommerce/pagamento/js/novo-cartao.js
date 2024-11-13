document.getElementById('novo-cartao').addEventListener('submit', function(event) {
    event.preventDefault();

    var isChecked = document.getElementById('principal').checked;

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        if (key === 'principal') {
            jsonData[key] = isChecked ? true : false;
        } else {
            jsonData[key] = value;
        }
    });

    sendCartaoToBackend(jsonData);
    this.reset();
});

function sendCartaoToBackend(data) {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id') || '1';

    fetch(`http://localhost:8080/cartoes/${id}`, {
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
