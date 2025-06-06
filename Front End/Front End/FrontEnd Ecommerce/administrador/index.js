document.getElementById('form').addEventListener('submit', function(event) {
    event.preventDefault(); 

    const email = document.getElementById('email').value;
    const nome = document.getElementById('username').value;
    const senha = document.getElementById('password').value;
    const confirmarSenha = document.getElementById('confirm_password').value;

    const formData = {
        email: email,
        nome: nome,
        senha: senha,
        confirmarSenha: confirmarSenha
    };

    fetch('http://localhost:8080/auth/criar/adm', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })

    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        window.location.href = "home-adm.html"
    })

    .catch((error) => {
        console.error('Error:', error);
    });
});
