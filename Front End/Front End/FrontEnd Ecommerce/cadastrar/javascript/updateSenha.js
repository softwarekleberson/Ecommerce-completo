async function enviarDados() {
    const token = localStorage.getItem("token");
    if (!token) {
        console.error("Erro: Token não encontrado. O usuário pode não estar autenticado.");
        return;
    }

    const senha = document.getElementById("senha").value;
    const confirmarSenha = document.getElementById("confirmarSenha").value;

    if (senha !== confirmarSenha) {
        alert("As senhas não coincidem. Tente novamente.");
        return;
    }

    const dados = {
        senha: senha,
        confirmarSenha: confirmarSenha
    };

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
