document.addEventListener('DOMContentLoaded', function () {

    const urlParams = new URLSearchParams(window.location.search);
    const clienteId = urlParams.get('clienteId') || 1;

    const endpoint = `http://localhost:8080/administrador/cupons/${clienteId}`;

    fetch(endpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar os dados do cupom.');
            }
            return response.json();
        })
        .then(data => {

            if (data.content && Array.isArray(data.content)) {

                const cupomContainer = document.querySelector('.cupom-container');
                data.content.forEach(cupom => {
                    const cupomElement = document.createElement('div');
                    cupomElement.classList.add('cupom');
                    const status = cupom.status ? 'Válido' : 'Inválido';
                    cupomElement.innerHTML = `
                        <h3>${cupom.tipoCupom}</h3>
                        <p>Código: ${cupom.idCupom}</p>
                        <p>Valor R$ ${cupom.valor}</p>
                        <p>Status: ${status}</p>
                    `;
                    cupomContainer.appendChild(cupomElement);
                });
            } else {

                console.error('Os dados retornados não são válidos.');

            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
});
