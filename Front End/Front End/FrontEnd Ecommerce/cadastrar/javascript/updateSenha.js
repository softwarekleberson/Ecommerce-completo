function enviarDados() {
    var idCliente = document.getElementById("idCliente").value;
    var senha = document.getElementById("senha").value;
    var confirmarSenha = document.getElementById("confirmarSenha").value;

    var dados = {
        idCliente: idCliente,
        senha: senha,
        confirmarSenha: confirmarSenha
    };

    var url = 'http://localhost:8080/cliente/senha'; 
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Sucesso:', data);
        window.location.href = "contas-listas.html"
        
    })
    .catch((error) => {
        console.error('Erro:', error);
        window.location.href = "contas-listas.html"
    });
}