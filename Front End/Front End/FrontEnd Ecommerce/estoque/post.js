document.getElementById('estoqueForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get('idLivro'), 10),
        quantidade: parseInt(formData.get('quantidade'), 10),
        valorCusto: formData.get('valorCusto'),
        dataEntrada: formData.get('dataEntrada'),
        fornecedor: formData.get('fornecedor'),
        estadoProduto: formData.get('estadoProduto')
    };

    fetch('http://localhost:8080/estoque', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(result => {
            console.log('Sucesso:', result);
        })
        .catch(error => {
            console.error('Erro:', error);
        })
        .finally(() => {
            document.getElementById('estoqueForm').reset();
        });
});
