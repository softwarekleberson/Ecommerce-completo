document.getElementById('myForm').addEventListener('submit', function(event) {
    var isChecked = document.getElementById('principal').checked;
    document.getElementById('principal').value = isChecked ? 'true' : 'false';
});

document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    sendDataToBackend(jsonData);
    this.reset();
    window.location.href = "cartoes.html"
});

function sendDataToBackend(data) {
   
    fetch('http://localhost:8080/cartoes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
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
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}


