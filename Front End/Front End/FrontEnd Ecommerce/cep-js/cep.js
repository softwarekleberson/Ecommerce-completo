document.getElementById('cep').addEventListener('blur', function() {
    let cep = this.value.replace(/\D/g, '');

    if (cep !== "") {
        let validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {
            fetch(`https://viacep.com.br/ws/${cep}/json/`)
                .then(response => response.json())
                .then(data => {
                    if (!("erro" in data)) {
                        document.getElementById('rua').value = data.logradouro;
                        document.getElementById('bairro').value = data.bairro;
                        document.getElementById('cidade').value = data.localidade;
                        document.getElementById('estado').value = data.uf;
                        document.getElementById('complemento').value = data.complemento;
                        document.getElementById('ddd').value = data.ddd;
                    } else {
                        alert("CEP não encontrado.");
                        limpaFormulario();
                    }
                })
                .catch(error => console.error('Erro ao buscar CEP:', error));
        } else {
            alert("Formato de CEP inválido.");
            limpaFormulario();
        }
    } else {
        limpaFormulario();
    }
});

function limpaFormulario() {
    document.getElementById('rua').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('cidade').value = "";
    document.getElementById('estado').value = "";
}
