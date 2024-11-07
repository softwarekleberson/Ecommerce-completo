const baseURL = "http://localhost:8080/pagamentos";
const pagamentoId = 1;  
const postUrl = `${baseURL}/${pagamentoId}`;

async function enviarPagamento() {
    const salvarCartao = document.getElementById("salvar").checked;
    const cupom1 = document.getElementById("cupom1").value;
    const cupom2 = document.getElementById("cupom2").value;

    const idCartao1Element = document.getElementById("idCartao1");
    const idCartao1 = idCartao1Element ? idCartao1Element.textContent.replace('id cartao 1 = ', '').trim() : null;

    // Verificação para garantir que pelo menos um método de pagamento foi selecionado
    if (!idCartao1 && !cupom1 && !cupom2) {
        alert("Por favor, forneça pelo menos um método de pagamento: cartão, cupom ou ambos.");
        return;
    }

    // Criação do objeto com os dados do pagamento
    const dadosPagamento = {
        salvarCartao: salvarCartao
    };

    if (idCartao1) {
        dadosPagamento.idCartao1 = parseInt(idCartao1);
    }

    if (cupom1) {
        dadosPagamento.cupom1 = cupom1;
    }

    if (cupom2) {
        dadosPagamento.cupom2 = cupom2;
    }

    try {
        const response = await fetch(postUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosPagamento)
        });

        if (response.ok) {
            alert("Pagamento enviado com sucesso!");
            console.log("Pagamento enviado com sucesso");
        } else {
            // Captura a mensagem de erro personalizada do backend
            const errorData = await response.json();
            alert(`Erro ao processar o pagamento: ${errorData.message || "Pagamento recusado. Tente novamente."}`);
            console.error("Erro ao processar o pagamento:", errorData);
        }
    } catch (error) {
        alert("Erro na requisição: " + error.message);
        console.error("Erro na requisição:", error);
    }
}

document.getElementById("finalizar").addEventListener("click", (event) => {
    event.preventDefault();  
    enviarPagamento();       
});
