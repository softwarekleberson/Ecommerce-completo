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
    const dadosCartao = {
        principal: document.getElementById("principal").checked,
        numeroCartao: document.getElementById("numeroCartao").value.trim(),
        nomeImpresso: document.getElementById("nomeImpresso").value.trim(),
        codigo: document.getElementById("codigo").value.trim(),
        dataExpiracao: document.getElementById("dataExpiracao").value,
        bandeira: document.getElementById("bandeira").value
    };

    console.log("Dados a serem enviados:", dadosCartao);
    console.log("Token JWT a ser enviado:", token);

    try {
        const response = await fetch("http://localhost:8080/cliente/cartoes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}` // Enviando o token corretamente
            },
            body: JSON.stringify(dadosCartao)
        });

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status} - ${response.statusText}`);
        }

        const resultado = await response.json();
        console.log("Resposta do servidor:", resultado);
        alert("Cartão cadastrado com sucesso!");

        // Redireciona para a página de cartões após o sucesso
        window.location.href = "cartoes.html";

    } catch (erro) {
        console.error("Erro ao enviar requisição:", erro);
        alert("Erro ao adicionar cartão. Verifique os dados e tente novamente.");
    }
});
