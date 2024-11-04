document.getElementById('cepEntrega').addEventListener('blur', function () {
    let cep = this.value.replace(/\D/g, '');
    if (cep.length === 8) {
        buscarCep(cep, preencherCamposEntrega);
    }
});

document.getElementById('cepCobranca').addEventListener('blur', function () {
    let cep = this.value.replace(/\D/g, '');
    if (cep.length === 8) {
        buscarCep(cep, preencherCamposCobranca);
    }
});

function buscarCep(cep, callback) {
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
            if (!data.erro) {
                callback(data);
            } else {
                alert('CEP não encontrado!');
            }
        })
        .catch(error => {
            console.error('Erro ao buscar CEP:', error);
            alert('Erro ao buscar CEP.');
        });
}

function preencherCamposEntrega(data) {
    document.getElementById('logradouroEntrega').value = data.logradouro;
    document.getElementById('bairroEntrega').value = data.bairro;
    document.getElementById('cidadeEntrega').value = data.localidade;
    document.getElementById('estadoEntrega').value = data.uf;
}

function preencherCamposCobranca(data) {
    document.getElementById('logradouroCobranca').value = data.logradouro;
    document.getElementById('bairroCobranca').value = data.bairro;
    document.getElementById('cidadeCobranca').value = data.localidade;
    document.getElementById('estadoCobranca').value = data.uf;
}
