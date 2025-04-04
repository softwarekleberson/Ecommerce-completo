document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault(); // Impede o recarregamento da página

    await enviarDados(); // Chama a função para processar os dados
});

async function enviarDados() {
    const token = localStorage.getItem("token");

    if (!token) {
        console.error("Erro: Token não encontrado. O usuário pode não estar autenticado.");
        alert("Erro de autenticação! Faça login novamente.");
        return;
    }

    const senha = document.getElementById("senha").value;
    const confirmarSenha = document.getElementById("confirmarSenha").value;

    // Verificação básica das senhas
    if (senha !== confirmarSenha) {
        alert("As senhas não coincidem. Tente novamente.");
        return;
    }

    if (senha.length < 8) {
        alert("A senha deve ter no mínimo 8 caracteres.");
        return;
    }

    const dados = { senha, confirmarSenha };

    console.log("Enviando dados:", dados);

    const url = "http://localhost:8080/cliente/senha";

    try {
        const response = await fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(dados)
        });

        const responseData = await response.json();

        if (!response.ok) {
            throw new Error(responseData.message || "Erro ao atualizar a senha");
        }

        console.log("Senha atualizada com sucesso:", responseData);
        alert("Senha alterada com sucesso!");
        window.location.href = "contas-listas.html";

    } catch (error) {
        console.error("Erro ao enviar os dados:", error);
        alert("Ocorreu um erro ao atualizar a senha. Verifique e tente novamente.");
    }
}
