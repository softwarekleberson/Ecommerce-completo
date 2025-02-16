document.getElementById("myForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Impede o recarregamento da página

    // Pegando o token correto do localStorage
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; // Redireciona para o login
        return;
    }

    // Pegando os dados do formulário
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
                "Authorization": `Bearer ${token}` // Enviando o token corretamente
            },
            body: JSON.stringify(dadosCobranca)
        });

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
        }

        const resultado = await response.json();
        console.log("Resposta do servidor:", resultado);
        alert("Endereço de cobrança cadastrado com sucesso!");

        // Redireciona para a página de cobranças após o sucesso
        window.location.href = "cobranca.html";

    } catch (erro) {
        console.error("Erro ao enviar requisição:", erro);
        alert("Erro ao adicionar endereço de cobrança. Verifique os dados e tente novamente.");
    }
});
