document.getElementById("entregaForm").addEventListener("submit", function(event) {
    event.preventDefault(); 

    const codigo = document.getElementById("codigo").value;

    const url = 'http://localhost:8080/administrador/status/entrega/entregue';

    const data = { 
        codigo: codigo
    };

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) 
    })

    .then(response => {
        if (response.ok) {
            return response.text().then(text => {
                return text ? JSON.parse(text) : {};
            });
        } else {
            throw new Error('Erro na requisição');
        }
    })

    .then(result => {
        alert('Status de entrega atualizado com sucesso!');
    })
    
    .catch(error => {
        console.error('Erro ao atualizar o status:', error);
        alert('Erro ao atualizar o status de entrega.');
    });
});
