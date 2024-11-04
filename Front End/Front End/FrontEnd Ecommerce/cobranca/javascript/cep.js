document.getElementById('cepCobranca').addEventListener('blur', function() {
    let cep = this.value.replace(/\D/g, '');  

    if (cep !== "") {
        let validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {
            fetch(`https://viacep.com.br/ws/${cep}/json/`)
                .then(response => response.json())
                .then(data => {
                    if (!("erro" in data)) {
                        
                        document.getElementById('logradouroCobranca').value = data.logradouro;
                        document.getElementById('bairroCobranca').value = data.bairro;
                        document.getElementById('cidadeCobranca').value = data.localidade;
                        document.getElementById('estadoCobranca').value = data.uf;
                    } else {
                        alert("CEP não encontrado.");
                        limpaFormulario();
                    }
                })
                .catch(error => console.error('Erro ao buscar o CEP:', error));
        } else {
            alert("Formato de CEP inválido.");
            limpaFormulario();
        }
    } else {
        limpaFormulario();
    }
});

function limpaFormulario() {
    document.getElementById('logradouroCobranca').value = "";
    document.getElementById('bairroCobranca').value = "";
    document.getElementById('cidadeCobranca').value = "";
    document.getElementById('estadoCobranca').value = "";
}
