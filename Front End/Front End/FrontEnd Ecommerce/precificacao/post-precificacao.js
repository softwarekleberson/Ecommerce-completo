document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);
    const precificacao = formData.get("precificacao");

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/admin/precificacao", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ precificacao })
        });

        if (!response.ok) {
            const errorResponse = await response.json();
            throw new Error(errorResponse.message || "Erro ao cadastrar precificação");
        }

        const data = await response.json();
        console.log("Success:", data);
        window.location.href = "home-adm.html"
        form.reset();

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao cadastrar precificação. Verifique os dados e tente novamente.");
    }
});
