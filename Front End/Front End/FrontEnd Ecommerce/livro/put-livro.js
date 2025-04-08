document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const token = localStorage.getItem("token");
        if (!token) {
            alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
            return;
        }

        const formData = new FormData(form);
        const data = {
            id: formData.get("id"),
            data: formData.get("data") || null,
            preco: formData.get("preco") ? parseFloat(formData.get("preco")) : null,
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
            edicao: formData.get("edicao") || null
        };

        try {
            const response = await fetch("http://localhost:8080/admin/livros", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) throw new Error("Erro ao atualizar o livro");

            window.location.href = "home-adm.html";

        } catch (error) {
            alert("Erro ao atualizar o livro. Verifique os dados e tente novamente.");
        }
    });
});
