document.getElementById('novo-cartao').addEventListener('submit', function (event) {
    event.preventDefault();

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Usuário não autenticado! Redirecionando para login...");
        window.location.href = "/login-cliente.html";
    }

    const isChecked = document.getElementById('principal').checked;

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = key === 'principal' ? isChecked : value;
    });

    sendCartaoToBackend(jsonData);
});

function sendCartaoToBackend(data) {
    fetch(`http://localhost:8080/cliente/cartoes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem("token")}`
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar dados para o backend');
            }
            return response.json();
        })
        .then(data => {
            console.log('Dados enviados com sucesso:', data);
            alert("Cartão cadastrado com sucesso!");
            document.getElementById('novo-cartao').reset();

            setTimeout(() => {
                location.reload();
            }, 2000);
        })
        .catch(error => {
            console.error('Erro:', error);
            alert("Erro ao cadastrar o cartão. Tente novamente.");
        });
}
