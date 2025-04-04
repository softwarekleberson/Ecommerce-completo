document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    const dadosCobranca = {
        principalCobranca: document.getElementById("principalCobranca").checked,
        receptorCobranca: document.getElementById("receptorCobranca").value.trim(),
        tipoResidenciaCobranca: document.getElementById("tipoResidenciaCobranca").value,
        tipoLogradouroCobranca: document.getElementById("tipoLogradouroCobranca").value,
        logradouroCobranca: document.getElementById("logradouroCobranca").value.trim(),
        numeroCobranca: document.getElementById("numeroCobranca").value.trim(),
        bairroCobranca: document.getElementById("bairroCobranca").value.trim(),
        cepCobranca: document.getElementById("cepCobranca").value.trim(),
        observacaoCobranca: document.getElementById("observacaoCobranca").value.trim(),
        cidadeCobranca: document.getElementById("cidadeCobranca").value.trim(),
        estadoCobranca: document.getElementById("estadoCobranca").value.trim(),
        paisCobranca: document.getElementById("paisCobranca").value.trim()
    };

    console.log("Dados a serem enviados:", dadosCobranca);
    console.log("Token JWT a ser enviado:", token);

    try {
        const response = await fetch("http://localhost:8080/cliente/cobrancas", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}` 
            },
            body: JSON.stringify(dadosCobranca)
        });

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
        }

        const resultado = await response.json();
        console.log("Resposta do servidor:", resultado);
        alert("Endereço de cobrança cadastrado com sucesso!");

        window.location.href = "cobranca.html";

    } catch (erro) {
        console.error("Erro ao enviar requisição:", erro);
        window.location.href = "cobranca.html"; 
    }
});
