document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html";
        return;
    }

    const id = obterIdDaURL();
    if (!id) {
        alert("Erro: ID da entrega não encontrado!");
        return;
    }

    await carregarDadosEntrega(id, token);
});

async function carregarDadosEntrega(id, token) {
    try {
        const url = `http://localhost:8080/cliente/entregas/${id}`;
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Erro ao carregar dados da entrega.");
        }

        const entrega = await response.json();
        preencherFormulario(entrega);

    } catch (error) {
        console.error("Erro ao carregar os dados da entrega:", error);
    }
}

function preencherFormulario(entrega) {
    document.getElementById("principal").checked = entrega.principal || false;
    document.getElementById("receptorEntrega").value = entrega.receptor || "";
    document.getElementById("tipoResidenciaEntrega").value = entrega.tipoResidencia.tipoResidencia || "";
    document.getElementById("tipoLogradouroEntrega").value = entrega.tipoLogradouro.tipoLogradouro || "";
    document.getElementById("logradouroEntrega").value = entrega.logradouro || "";
    document.getElementById("numeroEntrega").value = entrega.numero || "";
    document.getElementById("bairroEntrega").value = entrega.bairro || "";
    document.getElementById("cepEntrega").value = entrega.cep.cep || "";
    document.getElementById("observacaoEntrega").value = entrega.observacao || "";
    document.getElementById("cidadeEntrega").value = entrega.cidade.cidade || "";
    document.getElementById("estadoEntrega").value = entrega.cidade.estado.estado || "";
    document.getElementById("paisEntrega").value = entrega.pais.pais || "Brasil";
    document.getElementById("fraseEntregaEntrega").value = entrega.fraseEntrega || "";
}

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('entregaId');
}
