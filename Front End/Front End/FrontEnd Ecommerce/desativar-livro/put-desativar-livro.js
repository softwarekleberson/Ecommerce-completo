document.getElementById("desativar-livro").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get("idLivro"), 10),
        categoria: formData.get("categoria"),
        justificativa: formData.get("justificativa")
    };

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/admin/livros/inativar", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        // 🔹 Verifica se a resposta tem conteúdo antes de converter para JSON
        let result = null;
        const responseText = await response.text();
        if (responseText) {
            result = JSON.parse(responseText);
        }

        if (!response.ok) {
            throw new Error(result?.message || "Erro ao desativar o livro");
        }

        console.log("Sucesso:", result);
        alert("Livro desativado com sucesso!");
        this.reset();

    } catch (error) {
        console.error("Erro:", error);
        alert(error.message || "Erro ao desativar o livro.");
    }
});
