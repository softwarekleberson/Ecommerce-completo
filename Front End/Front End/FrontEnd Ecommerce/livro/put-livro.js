document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("livro");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        // Obtém o token de autenticação
        const token = localStorage.getItem("token");
        if (!token) {
            alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
            return;
        }

        // Captura os dados do formulário
        const formData = new FormData(form);
        const data = {
            id: formData.get("id"),
            data: formData.get("data") || null,
            preco: formData.get("preco") ? parseFloat(formData.get("preco")) : null,
            ativo: formData.get("ativo") === "on" || null,
            titulo: formData.get("titulo") || null,
            isbn: formData.get("isbn") || null,
            paginas: formData.get("paginas") ? parseInt(formData.get("paginas"), 10) : null,
            sinopse: formData.get("sinopse") || null,
            codigoBarra: formData.get("codigoBarra") || null,
            dimensoes: {
                altura: formData.get("altura") ? parseFloat(formData.get("altura")) : null,
                largura: formData.get("largura") ? parseFloat(formData.get("largura")) : null,
                peso: formData.get("peso") ? parseFloat(formData.get("peso")) : null,
                profundidade: formData.get("profundidade") ? parseFloat(formData.get("profundidade")) : null
            },
            editora: formData.get("editora") || null,
            precificacao: formData.get("precificacao") ? parseFloat(formData.get("precificacao")) : null,
            edicao: formData.get("edicao") || null
        };

        console.log("Dados enviados:", data);

        try {
            const response = await fetch("http://localhost:8080/admin/livros", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                const errorResponse = await response.json();
                throw new Error(errorResponse.message || "Erro ao atualizar o livro");
            }

            const result = await response.json();
            console.log("Resposta do servidor:", result);

            alert("Livro atualizado com sucesso!");
            window.location.href = "home-adm.html";

        } catch (error) {
            console.error("Erro:", error);
            alert("Erro ao atualizar o livro. Verifique os dados e tente novamente.");
        }
    });
});
