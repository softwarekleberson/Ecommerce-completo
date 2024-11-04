document.getElementById('nova-cobranca').addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);

    const data = {};
    formData.forEach((value, key) => {

        if (key === 'principal') {
            data[key] = this.principal.checked;

        } else {
            data[key] = value;
        }
    });

    fetch('http://localhost:8080/endereco/cobranca', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Sucesso:', data);
        })
        .catch((error) => {
            console.error('Erro:', error);
        });
});
