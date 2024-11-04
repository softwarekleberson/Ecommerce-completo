document.getElementById('desativar-livro').addEventListener('submit', function (event) {
    event.preventDefault(); 

    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get('idLivro'), 10),
        categoria: formData.get('categoria'),
        justificativa: formData.get('justificativa')
    };

    fetch('http://localhost:8080/livro/inativar', {
        method: 'PUT',
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
            document.getElementById('desativar-livro').reset();
        });
});
