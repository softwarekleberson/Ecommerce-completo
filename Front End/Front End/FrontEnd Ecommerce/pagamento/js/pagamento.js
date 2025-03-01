const baseURL = "http://localhost:8080/cliente/pagamentos";
const postUrl = `${baseURL}`;

document.getElementById("finalizar").addEventListener("click", async (event) => {
    event.preventDefault();
    await enviarPagamento();
});

async function enviarPagamento() {
    try {
        const token = localStorage.getItem("token");

        if (!token) {
            alert("Usuário não autenticado. Faça login novamente.");
            return;
        }

        const salvarCartao = document.getElementById("salvar").checked;
        const cupom1 = sanitizeInput(document.getElementById("cupom1")?.value);
        const cupom2 = sanitizeInput(document.getElementById("cupom2")?.value);

        const idCartao1Element = document.getElementById("idCartao1");
        const idCartao1 = idCartao1Element
            ? sanitizeInput(idCartao1Element.textContent.replace("id cartao 1 = ", "").trim())
            : null;

        if (!idCartao1 && !cupom1 && !cupom2) {
            alert("Por favor, forneça pelo menos um método de pagamento: cartão, cupom ou ambos.");
            return;
        }

        const dadosPagamento = { salvarCartao };

        if (idCartao1) {
            dadosPagamento.idCartao1 = parseInt(idCartao1);
        }
        if (cupom1) {
            dadosPagamento.cupom1 = cupom1;
        }
        if (cupom2) {
            dadosPagamento.cupom2 = cupom2;
        }

        console.log("Enviando dados do pagamento:", JSON.stringify(dadosPagamento, null, 2));

        const response = await fetch(postUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`,
            },
            body: JSON.stringify(dadosPagamento),
        });

        const responseText = await response.text();
        console.log("Resposta bruta do servidor:", responseText);

        let responseData;
        try {
            responseData = JSON.parse(responseText); 
        } catch (error) {
            responseData = { message: responseText }; 
        }

        if (!response.ok) {
            throw new Error(responseData.message || "Pagamento recusado. Tente novamente.");
        }

        alert("Pagamento enviado com sucesso!");
        console.log("Pagamento realizado:", responseData);
        window.location.reload();
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro na requisição: " + error.message);
    }
}

function sanitizeInput(value) {
    return value ? value.replace(/["\\]/g, "") : "";
}
