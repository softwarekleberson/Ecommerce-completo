document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('entrega');
    const deliveryForm = document.getElementById("delivery-form");
    const addDeliveryBtn = document.getElementById("add-delivery-btn");
    const cardForm = document.getElementById("card-form");
    const addCardBtn = document.getElementById("add-card-btn");
    const cardList = document.getElementById('cartao');

    function getClienteIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('idCliente') || 1;
    }

    function carregarEndereco(idCliente) {
        fetch(`http://localhost:8080/endereco/entrega/${idCliente}`)
            .then(response => response.json())
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
                            <p id="logradouro"> ${enderecoPrincipal.logradouro}</p>
                            <p id="tipoResidencia"> ${enderecoPrincipal.tipoResidencia} - ${enderecoPrincipal.numero} ${enderecoPrincipal.observacao}</p>
                            <p id="estado"> ${enderecoPrincipal.cidade}, ${enderecoPrincipal.estado} ${enderecoPrincipal.cep}</p>
                            <p id="pais"> ${enderecoPrincipal.pais}</p>                           
                        `;
                        entrega.appendChild(div); 
                        addDeliveryBtn.style.display = 'block'; 

                    } else {
                        deliveryForm.classList.remove("hidden"); 
                        addDeliveryBtn.style.display = 'none'; 
                    }
    
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error(error);
                deliveryForm.classList.remove("hidden"); 
                addDeliveryBtn.style.display = 'none'; 
            });
    }
    
    function carregarCobranca(idCliente) {
        fetch(`http://localhost:8080/endereco/cobranca/${idCliente}`)
            .then(response => response.json())
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
                            <p id="logradouro"> ${enderecoPrincipal.logradouro}</p>
                            <p id="tipoResidencia"> ${enderecoPrincipal.tipoResidencia} - ${enderecoPrincipal.numero} ${enderecoPrincipal.observacao}</p>
                            <p id="estado"> ${enderecoPrincipal.cidade}, ${enderecoPrincipal.estado} ${enderecoPrincipal.cep}</p>
                            <p id="pais"> ${enderecoPrincipal.pais}</p>                           
                        `;

                        cobranca.appendChild(div); 
                        addDeliveryBtn.style.display = 'block'; 

                    } else {
                        deliveryForm.classList.remove("hidden"); 
                        addDeliveryBtn.style.display = 'none'; 
                    }
    
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error(error);
                deliveryForm.classList.remove("hidden"); 
                addDeliveryBtn.style.display = 'none'; 
            });
    }
       
    function carregarCartao(idCliente) {
        fetch(`http://localhost:8080/cartoes/${idCliente}`)
            .then(response => response.json())
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    cardList.innerHTML = '';

                    const cartaoPrincipal = data.content.find(cartao => cartao.principal);

                    if (cartaoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h3>3 Método de pagamento</h3>
                            <p id="bandeira">Pagar com ${cartaoPrincipal.bandeira} </p>
                            <p id="nomeImpresso"> ${cartaoPrincipal.nomeImpresso}</p>
                            <p id="codigo">Código: ${cartaoPrincipal.codigo}</p>
                            <p>Escolha em quantas vezes parcelar</p>
                        `;
                        cardList.appendChild(div);
                        addCardBtn.style.display = 'block'; 

                    } else {
                        cardForm.classList.remove("hidden"); 
                        addCardBtn.style.display = 'none'; 
                    }
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error('Erro ao carregar usuários:', error);
                cardForm.classList.remove("hidden"); 
                addCardBtn.style.display = 'none'; 
            });
    }

    const idCliente = getClienteIdFromURL();
    if (idCliente) {
        carregarEndereco(idCliente);
        carregarCobranca(idCliente)
        carregarCartao(idCliente);
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }
});