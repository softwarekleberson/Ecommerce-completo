document.addEventListener("form", async () => {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Você precisa estar logado para visualizar o carrinho.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/cliente/pedidos", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status}`);
        }

        const data = await response.json();
        renderCarrinho(data);
    } catch (error) {
        console.error("Erro ao buscar dados:", error);
        alert("Erro ao carregar o carrinho. Tente novamente.");
    }
});

function renderCarrinho(data) {
    const tabelaCarrinho = document.querySelector("table tbody");
    if (!tabelaCarrinho) {
        console.error("Erro: Elemento da tabela não encontrado.");
        return;
    }

    tabelaCarrinho.innerHTML = "";

    if (!data.content || !Array.isArray(data.content) || data.content.length === 0) {
        tabelaCarrinho.innerHTML = "<tr><td colspan='6'>Seu carrinho está vazio.</td></tr>";
        return;
    }

    data.content.forEach(item => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td><a href="#"><img src="${item.primeiraImagem}" alt="${item.nome}"></a></td>
            <td class="descricao">
                <h4>${item.nome}</h4>
                <p>${item.dataPedido}</p>
            </td>
            <td>
                <select class="cor-seletor" name="quantidade" id="quantidade-${item.id}">
                    ${generateQuantityOptions(item.quantidade)}
                </select>
            </td>
            <td class="valor-produto">
                <p>R$ ${item.precoUnitario.toFixed(2)}</p>
            </td>
            <td>
                <p>R$ ${item.subtotal.toFixed(2)}</p>
            </td>
            <td>
                <i class="fa-solid fa-square-xmark remove-item" data-id="${item.id}" style="cursor: pointer;"></i>
            </td>
        `;
        tabelaCarrinho.appendChild(row);

        document.getElementById(`quantidade-${item.id}`).addEventListener("change", async (event) => {
            await atualizarQuantidade(item.id, event.target.value);
        });
    });

    const subtotal = data.content.reduce((acc, item) => acc + item.subtotal, 0);
    document.querySelector(".valor-subtotal").textContent = `R$ ${subtotal.toFixed(2)}`;
    document.querySelector(".valor-total").textContent = `R$ ${subtotal.toFixed(2)}`;

    document.querySelectorAll(".remove-item").forEach(button => {
        button.addEventListener("click", async (event) => {
            const itemId = event.target.getAttribute("data-id");
            await removerItem(itemId);
        });
    });
}

async function removerItem(itemId) {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Você precisa estar logado para remover um item.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/cliente/pedidos/itens/produto/${itemId}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Erro ao remover o item ${itemId}.`);
        }

        console.log(`Item ${itemId} removido com sucesso.`);
        location.reload();
    } catch (error) {
        console.error("Erro ao tentar remover o item:", error);
        alert("Erro ao remover o item. Tente novamente.");
    }
}

async function atualizarQuantidade(itemId, novaQuantidade) {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Erro: Você precisa estar logado para atualizar a quantidade.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/cliente/pedidos/itens/produto/${itemId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ quantidade: novaQuantidade })
        });

        if (!response.ok) {
            throw new Error(`Erro ao atualizar a quantidade do item ${itemId}.`);
        }

        console.log(`Quantidade do item ${itemId} atualizada para ${novaQuantidade}.`);
        location.reload();
    } catch (error) {
        console.error("Erro ao tentar atualizar a quantidade:", error);
        alert("Erro ao atualizar a quantidade. Tente novamente.");
    }
}

function generateQuantityOptions(quantidadeSelecionada) {
    let options = "";
    for (let i = 1; i <= 10; i++) {
        options += `<option value="${i}" ${i == quantidadeSelecionada ? "selected" : ""}>${i}</option>`;
    }
    return options;
}
