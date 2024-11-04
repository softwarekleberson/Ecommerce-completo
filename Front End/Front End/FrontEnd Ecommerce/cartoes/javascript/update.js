document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const principal = document.getElementById('principal').checked;
    const nomeImpresso = document.getElementById('nomeImpresso').value;
    const codigo = document.getElementById('codigo').value;

    const id = obterIdDaURL(); 
    console.log(id)

    const jsonData = {
        principal: principal || null,
        nomeImpresso: nomeImpresso || null,
        codigo: codigo || null,
    };

    const url = `http://localhost:8080/cartoes/${id}`; 
    sendDataToBackend(jsonData, url);

    this.reset();
   window.location.href = "cartoes.html";
});

function obterIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('cartaoId');
}

function sendDataToBackend(data, url) {
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error sending data to the backend');
        }
        return response.json();
    })
    .then(data => {
        console.log('Data sent successfully:', data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
