async function enviarDados() {
    const token = localStorage.getItem("token");
    if (!token) {
        console.error("Erro: Token não encontrado. O usuário pode não estar autenticado.");
        alert("Erro: Usuário não autenticado. Faça login novamente.");
        return;
    }

    const dados = {
        nome: document.getElementById("nome").value.trim() || null,
        nascimento: document.getElementById("nascimento").value || null,
        ddd: document.getElementById("ddd").value.trim() || null,
        telefone: document.getElementById("telefone").value.trim() || null,
        tipo: document.getElementById("tipo").value || null
    };

    console.log("Enviando dados:", dados);

    const url = "http://localhost:8080/cliente/atualizar";

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
            throw new Error(responseData.message || "Erro ao atualizar cliente");
        }

        console.log("Dados atualizados com sucesso:", responseData);
        window.location.href = "contas-listas.html";

    } catch (error) {
        window.location.href = "contas-listas.html";
    }
}

document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault();
    await enviarDados();
});
