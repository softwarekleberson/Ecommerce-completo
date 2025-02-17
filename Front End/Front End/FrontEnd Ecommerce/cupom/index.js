document.addEventListener("DOMContentLoaded", async function () {
    const endpoint = "http://localhost:8080/cliente/cupons";

    const token = localStorage.getItem("token");
    if (!token) {
        console.error("Erro: Token de autenticação não encontrado.");
        return;
    }

    try {
        const response = await fetch(endpoint, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            let errorMessage = `Erro ao carregar os dados do cupom (${response.status})`;

            try {
                const errorResponse = await response.json();
                errorMessage = errorResponse.message || errorMessage;
            } catch (jsonError) {
                console.warn("A resposta do servidor não é um JSON válido.");
            }

            throw new Error(errorMessage);
        }

        let data = null;
        const responseText = await response.text();
        if (responseText) {
            data = JSON.parse(responseText);
        }

        if (data && data.content && Array.isArray(data.content)) {
            const cupomContainer = document.querySelector(".cupom-container");
            cupomContainer.innerHTML = ""; 
            data.content.forEach((cupom) => {
                const cupomElement = document.createElement("div");
                cupomElement.classList.add("cupom");
                const status = cupom.status ? "Válido" : "Inválido";
                cupomElement.innerHTML = `
                    <h3>${cupom.tipoCupom}</h3>
                    <p>Código: ${cupom.idCupom}</p>
                    <p>Valor R$ ${cupom.valor.toFixed(2)}</p>
                    <p>Status: ${status}</p>
                `;
                cupomContainer.appendChild(cupomElement);
            });
        } else {
            console.warn("Os dados retornados não são válidos ou estão vazios.");
        }
    } catch (error) {
        console.error("Erro:", error);
    }
});
