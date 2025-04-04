document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html";
        return;
    }

    await carregarDadosCliente(token);
});

async function carregarDadosCliente(token) {
    try {
        const url = `http://localhost:8080/cliente/unico`; 
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Erro ao carregar dados do cliente.");
        }

        const cliente = await response.json();
        preencherFormulario(cliente);

    } catch (error) {
        console.error("Erro ao carregar os dados do cliente:", error);
    }
}

function preencherFormulario(cliente) {
    document.getElementById("nome").value = cliente.nome || "";
    document.getElementById("nascimento").value = cliente.nascimento || "";
    document.getElementById("ddd").value = cliente.ddd || "";
    document.getElementById("telefone").value = cliente.telefone || "";
    document.getElementById("tipo").value = cliente.tipo || "";
}
