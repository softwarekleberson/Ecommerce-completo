document.getElementById("form").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Erro: Token JWT não encontrado! Faça login novamente.");
        window.location.href = "login.html"; 
        return;
    }

    const principal = document.getElementById('principal').checked;
    const nomeImpresso = document.getElementById('nomeImpresso').value.trim();
    const codigo = document.getElementById('codigo').value.trim();

    const id = obterIdDaURL();

    if (!id) {
        alert("Erro: ID do cartão não encontrado na URL.");
        return;
    }

    const jsonData = {
        principal: principal || null,
        nomeImpresso: nomeImpresso || null,
        codigo: codigo || null,
    };

    const url = `http://localhost:8080/cliente/cartoes/${id}`;

    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` 
            },
            body: JSON.stringify(jsonData),
        });

        if (!response.ok) {
            throw new Error(`Erro ao atualizar cartão: ${response.status} - ${response.statusText}`);
        }

        const resultado = await response.json();
        console.log("Cartão atualizado com sucesso:", resultado);
        alert("Cartão atualizado com sucesso!");

        window.location.href = "cartoes.html";

    } catch (erro) {
        console.error("Erro ao atualizar cartão:", erro);
        alert("Erro ao atualizar cartão. Verifique os dados e tente novamente.");
    }
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('cartaoId');
}
