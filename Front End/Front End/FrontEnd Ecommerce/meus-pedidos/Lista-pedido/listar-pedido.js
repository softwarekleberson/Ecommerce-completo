document.addEventListener('DOMContentLoaded', async () => {
    const ordersContainer = document.getElementById('orders-container');

    const createOrderHTML = (order) => {
        console.log(order)
        
        return `
        <div class="caixa-principal">
            <div class="cabecalho">
                <div class="informacoes-pedito">
                    <p>Pedido realizado</p>
                    <p>Total</p>
                    <p>Pedido troca</p>
                    <p>Nº pedido</p>
                </div>
                <div class="informacoes-pedito-valores">
                    <p>${order.dataPedido}</p>
                    <p>R$ ${order.subtotal}</p>
                    <p>
                        ${order.trocaDevolucao === "DEVOLUCAO_NAO_PEDIDA"
                ? "Devolução não solicitada"
                : order.trocaDevolucao === "DEVOLUCAO_PEDIDO"
                    ? "Troca solicitada"
                    : order.trocaDevolucao}
                    </p>
                    <p class="codigo-pedido">${order.codigoPedido}</p>
                </div>
            </div>
            <div>
                <p class="data-entrega">
                    Pagamento: ${order.status === "EM_PROCESSAMENTO" ? "Em processamento" : order.status}
                </p>
                <div class="imagem-e-descricao">
                    <img src="${order.primeiraImagem}" alt="">
                    <p class="data-entrega">Entregue ${order.entregue}</p>
                    <p class="nome-produto">${order.nome}</p>
                    <p class="quantidade-produto">Quantidade ${order.quantidade}</p>
                    <button class="botao-devolucao">Troca</button>
                </div>
            </div>
        </div>
        `;
    };

    const fetchOrders = async () => {
        try {
            const token = localStorage.getItem('token');
            if (!token) {
                console.error("Token não encontrado!");
                return;
            }

            const response = await fetch(`http://localhost:8080/cliente/pedidos/pagos`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Erro ao buscar o pedido');
            }

            const data = await response.json();
            const orders = data.content;

            ordersContainer.innerHTML = '';
            orders.forEach(order => {
                const orderHTML = createOrderHTML(order);
                ordersContainer.innerHTML += orderHTML;
            });

            document.querySelectorAll('.botao-devolucao').forEach((button) => {
                button.addEventListener('click', async () => {
                    const confirmDevolucao = confirm('Você tem certeza que deseja solicitar a troca deste pedido?');
                    if (confirmDevolucao) {
                        const orderElement = button.closest('.caixa-principal');
                        const codigoPedido = orderElement.querySelector('.codigo-pedido').textContent;

                        const url = `http://localhost:8080/cliente/devolucoes`;

                        const requestBody = {
                            codigoPedido: codigoPedido
                        };

                        try {
                            const postResponse = await fetch(url, {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json',
                                    'Authorization': `Bearer ${token}`
                                },
                                body: JSON.stringify(requestBody)
                            });

                            if (postResponse.ok) {
                                alert('Devolução solicitada com sucesso.');
                            } else {
                                console.error('Erro ao solicitar troca:', postResponse.statusText);
                            }
                        } catch (error) {
                            console.error('Erro ao enviar a requisição de troca:', error);
                        }
                    }
                });
            });

        } catch (error) {
            console.error('Erro ao buscar o pedido:', error);
        }
    };

    await fetchOrders();
});
