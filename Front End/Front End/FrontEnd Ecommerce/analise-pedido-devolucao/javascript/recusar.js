document.getElementById('recusar').addEventListener('click', function (event) {
    event.preventDefault();

    const codigoPedido = document.getElementById('codigoPedido').value;
    const codigoDevolucao = document.getElementById('codigoDevolucao').value;
    const esperandoDevolucaoOuRecebido = document.getElementById('esperandoDevolucaoOuRecebido').value;
    const produtoVoltaParaEstoque = document.getElementById('produtoVoltaParaEstoque').value;
    const estadoProduto = document.getElementById('estadoProduto').value;
    const token = localStorage.getItem('token'); 

    if (!token) {
        console.error("Token não encontrado!");
        return;
    }

    const data = {
        codigoPedido: codigoPedido,
        codigoDevolucao: codigoDevolucao,
        esperandoDevolucaoOuRecebido: esperandoDevolucaoOuRecebido,
        produtoVoltaParaEstoque: produtoVoltaParaEstoque,
        estadoProduto: estadoProduto
    };

    fetch('http://localhost:8080/admin/recusar', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` 
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                window.location.href = "home-adm.html"
            } else {
                alert('Ocorreu ao recusar devolução.');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu ao recusar devolução.');
        });
});
