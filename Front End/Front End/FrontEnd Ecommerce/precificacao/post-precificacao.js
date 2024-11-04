document.getElementById('precificacao').addEventListener('submit', function (event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);
    const precificacao = formData.get('precificacao');

    fetch('http://localhost:8080/precificacao', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            precificacao: precificacao
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            form.reset();
        })
        .catch(error => {
            console.error('Error:', error);
        });
});