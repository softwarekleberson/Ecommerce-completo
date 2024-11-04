document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('myForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const dadosCliente = {
            genero: form.genero.value,
            nome: form.nome.value,
            nascimento: form.nascimento.value,
            cpf: form.cpf.value,
            senha: form.senha.value,
            confirmarSenha: form.confirmarSenha.value,
            email: form.email.value,
            ddd: form.ddd.value,
            telefone: form.telefone.value,
            tipo: form.tipo.value,
            entrega: [{
                receptorEntrega: form.receptorEntrega.value,
                tipoResidenciaEntrega: form.tipoResidenciaEntrega.value,
                tipoLogradouroEntrega: form.tipoLogradouroEntrega.value,
                logradouroEntrega: form.logradouroEntrega.value,
                numeroEntrega: form.numeroEntrega.value,
                bairroEntrega: form.bairroEntrega.value,
                cepEntrega: form.cepEntrega.value,
                observacaoEntrega: form.observacaoEntrega.value,
                cidadeEntrega: form.cidadeEntrega.value,
                estadoEntrega: form.estadoEntrega.value,
                paisEntrega: form.paisEntrega.value,
                fraseEntregaEntrega: form.fraseEntregaEntrega.value
            }],
            cobranca: [{
                receptorCobranca: form.receptorCobranca.value,
                tipoResidenciaCobranca: form.tipoResidenciaCobranca.value,
                tipoLogradouroCobranca: form.tipoLogradouroCobranca.value,
                logradouroCobranca: form.logradouroCobranca.value,
                numeroCobranca: form.numeroCobranca.value,
                bairroCobranca: form.bairroCobranca.value,
                cepCobranca: form.cepCobranca.value,
                observacaoCobranca: form.observacaoCobranca.value,
                cidadeCobranca: form.cidadeCobranca.value,
                estadoCobranca: form.estadoCobranca.value,
                paisCobranca: form.paisCobranca.value
            }]
        };

        const url = 'http://localhost:8080/cliente';

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosCliente)
        };

        fetch(url, options)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Erro ao enviar dados do cliente.');
            })
            .then(data => {
                console.log('Dados enviados com sucesso:', data);
                form.reset();
                window.location.href = "home.html"
            })
            .catch(error => {
                console.error('Erro:', error);
                
                if (error.response && error.response.status === 400) {
                    error.response.json().then(errorData => {
                        
                        Object.keys(errorData.errors).forEach(field => {
                            const errorMessage = errorData.errors[field].join(', ');
                            const errorField = document.getElementById(field + 'Error');
                            if (errorField) {
                                errorField.textContent = errorMessage;
                            }
                        });
                    });
                }
            });            
    });
});