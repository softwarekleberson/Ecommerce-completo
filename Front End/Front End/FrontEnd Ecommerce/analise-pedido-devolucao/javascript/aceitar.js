document.getElementById('aceitar').addEventListener('click', function (event) {
    event.preventDefault();

    const codigoPedido = document.getElementById('codigoPedido').value;
    const codigoDevolucao = document.getElementById('codigoDevolucao').value;
    const esperandoDevolucaoOuRecebido = document.getElementById('esperandoDevolucaoOuRecebido').value;
    const produtoVoltaParaEstoque = document.getElementById('produtoVoltaParaEstoque').value;
    const estadoProduto = document.getElementById('estadoProduto').value;

    const data = {
        codigoPedido: codigoPedido,
        codigoDevolucao: codigoDevolucao,
        esperandoDevolucaoOuRecebido: esperandoDevolucaoOuRecebido,
        produtoVoltaParaEstoque: produtoVoltaParaEstoque,
        estadoProduto: estadoProduto
    };

    // Faz a requisição PUT
    fetch('http://localhost:8080/administrador/aceitar', {

        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {

                alert('Devolução aceita com sucesso!');
                document.getElementById('formDevolucao').reset();

            } else {
                alert('Erro ao aceitar devolução.');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao processar sua solicitação.');
        });
});
