function enviarDados() {
    var idCliente = document.getElementById("idCliente").value;
    var genero = document.getElementById("genero").value;
    var nome = document.getElementById("nome").value;
    var nascimento = document.getElementById("nascimento").value;
    var email = document.getElementById("email").value;
    var ddd = document.getElementById("ddd").value;
    var telefone = document.getElementById("telefone").value;
    var tipo = document.getElementById("tipo").value;

    var dados = {
        idCliente: idCliente,
        genero: genero || null,
        nome: nome || null,
        nascimento: nascimento || null,
        email: email || null,
        ddd: ddd || null,
        telefone: telefone || null,
        tipo: tipo || null
    };

    console.log(dados)

    var url = 'http://localhost:8080/cliente'; 
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    })

    .then(response => response.json())
    .then(data => {
        window.location.href = "contas-listas.html"
    })

    .catch((error) => {
        if(error){
            window.location.href = "contas-listas.html"
        }
    });
}