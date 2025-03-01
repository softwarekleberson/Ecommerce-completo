document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('entrega');
    const deliveryForm = document.getElementById("delivery-form");
    const addDeliveryBtn = document.getElementById("add-delivery-btn");
    const cardForm = document.getElementById("card-form");
    const addCardBtn = document.getElementById("add-card-btn");
    const cardList = document.getElementById('cartao');

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Usuário não autenticado! Redirecionando para login...");
        window.location.href = "login-cliente.html";
    }

    function fetchWithAuth(url, options = {}) {
        return fetch(url, {
            ...options,
            headers: {
                ...options.headers,
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.status === 401) {
                alert("Sessão expirada! Faça login novamente.");
                localStorage.removeItem("jwtToken");
                window.location.href = "/login-cliente.html";
            }
            return response.json();
        })
        .catch(error => console.error('Erro:', error));
    }

    function carregarEndereco() {
        fetchWithAuth(`http://localhost:8080/cliente/entregas`)
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    const entrega = document.getElementById('entrega');
                    entrega.innerHTML = '';

                    const enderecoPrincipal = data.content.find(endereco => endereco.principal);

                    if (enderecoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h3>1 Endereço entrega principal</h3>
                            <p>${enderecoPrincipal.logradouro}</p>
                            <p>${enderecoPrincipal.tipoResidencia} - ${enderecoPrincipal.numero} ${enderecoPrincipal.observacao}</p>
                            <p>${enderecoPrincipal.cidade}, ${enderecoPrincipal.estado} ${enderecoPrincipal.cep}</p>
                            <p>${enderecoPrincipal.pais}</p>                           
                        `;
                        entrega.appendChild(div);
                        addDeliveryBtn.style.display = 'block';

                    } else {
                        deliveryForm.classList.remove("hidden");
                        addDeliveryBtn.style.display = 'none';
                    }

                    setTimeout(() => location.reload(), 2000);
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            });
    }

    function carregarCobranca() {
        fetchWithAuth(`http://localhost:8080/cliente/cobrancas`)
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    const cobranca = document.getElementById('cobranca');
                    cobranca.innerHTML = '';

                    const enderecoPrincipal = data.content.find(endereco => endereco.principal);

                    if (enderecoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h3>2 Endereço cobrança principal</h3>
                            <p>${enderecoPrincipal.logradouro}</p>
                            <p>${enderecoPrincipal.tipoResidencia} - ${enderecoPrincipal.numero} ${enderecoPrincipal.observacao}</p>
                            <p>${enderecoPrincipal.cidade}, ${enderecoPrincipal.estado} ${enderecoPrincipal.cep}</p>
                            <p>${enderecoPrincipal.pais}</p>                           
                        `;
                        cobranca.appendChild(div);
                        addDeliveryBtn.style.display = 'block';

                    } else {
                        deliveryForm.classList.remove("hidden");
                        addDeliveryBtn.style.display = 'none';
                    }

                    setTimeout(() => location.reload(), 2000);
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            });
    }

    function carregarCartao() {
        fetchWithAuth(`http://localhost:8080/cliente/cartoes`)
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    cardList.innerHTML = '';

                    const cartaoPrincipal = data.content.find(cartao => cartao.principal);

                    if (cartaoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h3>3 Método de pagamento</h3>
                            <p id="idCartao1" hidden>id cartao 1 = ${cartaoPrincipal.id}</p>
                            <p>Pagar com ${cartaoPrincipal.bandeira} </p>
                            <p>${cartaoPrincipal.nomeImpresso}</p>
                            <p>Código: ${cartaoPrincipal.codigo}</p>
                            <p>Escolha em quantas vezes parcelar</p>
                        `;
                        cardList.appendChild(div);
                        addCardBtn.style.display = 'block';

                    } else {
                        cardForm.classList.remove("hidden");
                        addCardBtn.style.display = 'none';
                    }

                    setTimeout(() => location.reload(), 2000);
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            });
    }

    carregarEndereco();
    carregarCobranca();
    carregarCartao();
});
