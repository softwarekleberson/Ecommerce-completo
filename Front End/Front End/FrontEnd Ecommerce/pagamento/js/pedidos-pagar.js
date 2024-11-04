
document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const pedidoId = urlParams.get('idCliente') || 1; 

    fetch(`http://localhost:8080/pedidos/${pedidoId}`)
        .then(response => response.json())
        .then(data => {
            const pedidosSection = document.getElementById('pedidos-pagar');
            pedidosSection.innerHTML = ''; 
            
            let totalGeral = 0; 

            data.content.forEach(pedido => {
                const pedidoElement = document.createElement('div');
                pedidoElement.classList.add('pedido-item');

                pedidoElement.innerHTML = `
                    <h3>${pedido.nome}</h3>
                    <img src="${pedido.primeiraImagem}" alt="${pedido.nome}" width="100" />
                    <p>Quantidade: ${pedido.quantidade}</p>
                    <p>Preço Unitário: R$${pedido.precoUnitario.toFixed(2)}</p>
                    <p>Subtotal: R$${pedido.subtotal.toFixed(2)}</p>
                    <hr>
                `;
                
                pedidosSection.appendChild(pedidoElement);

                totalGeral += pedido.subtotal;
            });

            const totalElement = document.createElement('div');
            totalElement.classList.add('total-geral');
            totalElement.innerHTML = `
                <h3 class="preco-final">Total: R$${totalGeral.toFixed(2)}</h3>
                <p>Em 1 x no cartão R$${totalGeral.toFixed(2)}</p>
            `;
            
            pedidosSection.appendChild(totalElement); 
        })
        .catch(error => {
            console.error('Erro ao buscar os pedidos:', error);
        });
});
