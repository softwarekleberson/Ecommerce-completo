document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get("idLivro"), 10),
        quantidade: parseInt(formData.get("quantidade"), 10),
        valorCusto: formData.get("valorCusto"),
        dataEntrada: formData.get("dataEntrada"),
        fornecedor: formData.get("fornecedor"),
        estadoProduto: formData.get("estadoProduto")
    };

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/admin/estoque", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorResponse = await response.json();
            throw new Error(errorResponse.message || "Erro ao cadastrar estoque");
        }

        const result = await response.json();
        console.log("Sucesso:", result);
        window.location.href = "home-adm.html"

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao cadastrar estoque. Verifique os dados e tente novamente.");
    }
});
