async function enviarDados() {
    const token = localStorage.getItem("token");
    if (!token) {
        console.error("Erro: Token não encontrado. O usuário pode não estar autenticado.");
        return;
    }

    const dados = {
        genero: document.getElementById("genero").value || null,
        nome: document.getElementById("nome").value || null,
        nascimento: document.getElementById("nascimento").value || null,
        email: document.getElementById("email").value || null,
        ddd: document.getElementById("ddd").value || null,
        telefone: document.getElementById("telefone").value || null,
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
        console.error("Erro ao enviar os dados:", error);
        alert("Ocorreu um erro ao atualizar os dados. Verifique e tente novamente.");
    }
}
