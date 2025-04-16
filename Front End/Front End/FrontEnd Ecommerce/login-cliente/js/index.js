document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("form").addEventListener("submit", async function (event) {
        event.preventDefault();
        
        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        const dados = { email, senha };

        try {

            const response = await fetch("http://localhost:8080/auth/login/cliente", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(dados),
            });

            if (!response.ok) {
                throw new Error("Falha no login. Verifique suas credenciais.");
            }

            const resultado = await response.json();

            if (resultado.token) {
                localStorage.setItem("token", resultado.token);

                setTimeout(function() {
                    window.location.href = "../home.html";
                }, 3000);
            } else {
                console.error("Token não encontrado na resposta.");
                alert("Erro: Token não recebido.");
            }

        } catch (error) {
            console.error("Erro ao fazer login:", error);
            alert("Erro ao fazer login. Tente novamente.");
        }
    });
});
