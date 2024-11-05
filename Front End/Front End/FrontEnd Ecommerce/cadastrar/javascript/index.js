document.getElementById("myForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = {
        genero: document.getElementById("genero").value,
        nome: document.getElementById("nome").value,
        nascimento: document.getElementById("nascimento").value,
        cpf: document.getElementById("cpf").value,
        senha: document.getElementById("senha").value,
        confirmarSenha: document.getElementById("confirmarSenha").value,
        email: document.getElementById("email").value,
        ddd: document.getElementById("ddd").value,
        telefone: document.getElementById("telefone").value,
        tipo: document.getElementById("tipo").value,
        entrega: [
            {
                receptorEntrega: document.getElementById("receptorEntrega").value,
                principalEntrega: document.getElementById("principalEntrega").checked,
                tipoResidenciaEntrega: document.getElementById("tipoResidenciaEntrega").value,
                tipoLogradouroEntrega: document.getElementById("tipoLogradouroEntrega").value,
                logradouroEntrega: document.getElementById("logradouroEntrega").value,
                numeroEntrega: document.getElementById("numeroEntrega").value,
                bairroEntrega: document.getElementById("bairroEntrega").value,
                cepEntrega: document.getElementById("cepEntrega").value,
                observacaoEntrega: document.getElementById("observacaoEntrega").value,
                cidadeEntrega: document.getElementById("cidadeEntrega").value,
                estadoEntrega: document.getElementById("estadoEntrega").value,
                paisEntrega: document.getElementById("paisEntrega").value,
                fraseEntregaEntrega: document.getElementById("fraseEntregaEntrega").value
            }
        ],
        cobranca: [
            {
                receptorCobranca: document.getElementById("receptorCobranca").value,
                principalCobranca: document.getElementById("principalCobranca").checked,
                tipoResidenciaCobranca: document.getElementById("tipoResidenciaCobranca").value,
                tipoLogradouroCobranca: document.getElementById("tipoLogradouroCobranca").value,
                logradouroCobranca: document.getElementById("logradouroCobranca").value,
                numeroCobranca: document.getElementById("numeroCobranca").value,
                bairroCobranca: document.getElementById("bairroCobranca").value,
                cepCobranca: document.getElementById("cepCobranca").value,
                observacaoCobranca: document.getElementById("observacaoCobranca").value,
                cidadeCobranca: document.getElementById("cidadeCobranca").value,
                estadoCobranca: document.getElementById("estadoCobranca").value,
                paisCobranca: document.getElementById("paisCobranca").value
            }
        ]
    };

    try {
        const response = await fetch("http://localhost:8080/cliente", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            console.error("Erro ao enviar o formulário:", errorData);
            throw new Error(`Erro ao enviar o formulário: ${errorData.message || 'Erro desconhecido'}`);
        }

        const result = await response.json();
        console.log("Formulário enviado com sucesso!", result);
        alert("Formulário enviado com sucesso!");

        document.getElementById("myForm").reset();
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro: " + error.message);
    }
});
