document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData);

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Token de autenticação não encontrado. Faça login novamente.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/admin/cupons/gerar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            let errorMessage = `Erro: ${response.statusText}`;
            
            try {
                const errorResponse = await response.json();
                errorMessage = errorResponse.message || errorMessage;
            } catch (jsonError) {
                console.warn("A resposta do servidor não é um JSON válido.");
            }

            throw new Error(errorMessage);
        }

        let result = null;
        const responseText = await response.text();
        if (responseText) {
            result = JSON.parse(responseText);
        }

        console.log("Sucesso:", result);
        window.location.href = "home-adm.html"
        event.target.reset();

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao cadastrar novo cupom");
    }
});
