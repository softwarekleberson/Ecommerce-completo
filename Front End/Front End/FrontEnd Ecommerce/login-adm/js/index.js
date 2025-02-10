document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("myForm").addEventListener("submit", async function (event) {
        event.preventDefault();
        
        console.log("Evento submit acionado!");

        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        console.log("Email:", email);
        console.log("Senha:", senha);

        const dados = { email, senha };

        try {
            console.log("Enviando requisição para API...");

            const response = await fetch("http://localhost:8080/auth/login/adm", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(dados),
            });

            console.log("Resposta recebida, status:", response.status);

            if (!response.ok) {
                throw new Error("Falha no login. Verifique suas credenciais.");
            }

            const resultado = await response.json();
            console.log("Login bem-sucedido:", resultado);

            if (resultado.token) {
                localStorage.setItem("token", resultado.token);
                console.log("Token salvo no localStorage:", resultado.token);

                setTimeout(function() {
                    window.location.href = "/home-adm.html";
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
