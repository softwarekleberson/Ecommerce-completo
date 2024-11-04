function buscarCEP() {
    const cep = document.getElementById('cepEntrega').value.replace(/\D/g, '');

    if (cep.length === 8) {
        fetch(`https://viacep.com.br/ws/${cep}/json/`)
            .then(response => response.json())
            .then(data => {
                if (!data.erro) {
                    document.getElementById('logradouroEntrega').value = data.logradouro;
                    document.getElementById('bairroEntrega').value = data.bairro;
                    document.getElementById('cidadeEntrega').value = data.localidade;
                    document.getElementById('estadoEntrega').value = data.uf;
                } else {
                    alert('CEP não encontrado.');
                }
            })
            .catch(error => {
                console.error('Erro ao buscar CEP:', error);
            });
    } else {
        alert('CEP inválido!');
    }
}
