document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html";
        return;
    }

    const id = obterIdDaURL();
    if (!id) {
        alert("Erro: ID da cobranca não encontrado!");
        return;
    }

    await carregarDadosCobranca(id, token);
});

async function carregarDadosCobranca(id, token) {
    try {
        const url = `http://localhost:8080/cliente/cobrancas/${id}`;
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Erro ao carregar dados da cobrança.");
        }

        const cobranca = await response.json();
        preencherFormulario(cobranca);

    } catch (error) {
        console.error("Erro ao carregar os dados da cobrança:", error);
    }
}

function preencherFormulario(cobranca) {
    document.getElementById("principal").checked = cobranca.principal || false;
    document.getElementById("receptorCobranca").value = cobranca.receptor || "";
    document.getElementById("tipoResidenciaCobranca").value = cobranca.tipoResidencia.tipoResidencia || "";
    document.getElementById("tipoLogradouroCobranca").value = cobranca.tipoLogradouro.tipoLogradouro || "";
    document.getElementById("logradouroCobranca").value = cobranca.logradouro || "";
    document.getElementById("numeroCobranca").value = cobranca.numero || "";
    document.getElementById("bairroCobranca").value = cobranca.bairro || "";
    document.getElementById("cepCobranca").value = cobranca.cep.cep || "";
    document.getElementById("observacaoCobranca").value = cobranca.observacao || "";
    document.getElementById("cidadeCobranca").value = cobranca.cidade.cidade || "";
    document.getElementById("estadoCobranca").value = cobranca.cidade.estado.estado || "";
    document.getElementById("paisCobranca").value = cobranca.cidade.pais.pais;
    document.getElementById("fraseCobrancaCobranca").value = cobranca.fraseCobranca || "";
}

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('cobrancaId');
}
