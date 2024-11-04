document.addEventListener('DOMContentLoaded', () => {
    const ordersContainer = document.getElementById('orders-container');

    const createOrderHTML = (order) => {
        return `
        <div class="caixa-principal">
            <div class="cabecalho">
                <div class="informacoes-pedito">
                    <p>Pedido realizado</p>
                    <p>Total</p>
                    <p>Entregue</p>
                    <p>Nº pedido</p>
                </div>
                <div class="informacoes-pedito-valores">
                    <p>${order.dataPedido}</p>
                    <p>R$ ${order.subtotal}</p>
                    <p>Pedido ID: ${order.idPedido}</p>
                    <p>${order.codigoPedido}</p>
                </div>
            </div>
            <div>
                <p class="status">Status: ${order.status}</p>
                <div class="imagem-e-descricao">
                <img src="${order.primeiraImagem}" alt="">
                <p class="data-entrega">Entregue ${order.entregue}</p>
                <p class="nome-produto">${order.nome}</p>
                <p class="quantidade-produto">${order.quantidade}</p>
                <button class="botao-devolucao">Devolução</button>
            </div>
        </div>
        `;
    };

    const fetchOrders = async (orderId) => {
        try {
            const response = await fetch(`http://localhost:8080/pedidos/pagos/${orderId}`);
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
        } catch (error) {
            console.error('Erro ao buscar o pedido:', error);
        }
    };

    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('clienteId') || 1;

    if (orderId) {
        console.log('Order ID:', orderId);
        fetchOrders(orderId);
    } else {
        console.error('ID do pedido não encontrado na URL');
    }
});
