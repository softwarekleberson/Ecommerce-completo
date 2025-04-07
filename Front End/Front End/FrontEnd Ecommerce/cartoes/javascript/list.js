document.addEventListener('DOMContentLoaded', function () {
  const userList = document.getElementById('userList');
  const token = localStorage.getItem('token');

  if (!token) {
    alert("Erro: Token JWT não encontrado! Faça login novamente.");
    window.location.href = "login.html";
    return;
  }

  // Cria o card fixo "Adicionar Cobrança"
  const cardFixo = document.createElement('div');
  cardFixo.classList.add('card');
  cardFixo.innerHTML = `
    <h2>Adicionar Cartão</h2>
    <div class="actions">
      <a class="link" href="adiciona-cartao.html">Adicionar</a>
    </div>
  `;
  userList.appendChild(cardFixo);

  // Requisição para buscar cartões
  fetch('http://localhost:8080/cliente/cartoes', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`Erro ao buscar cartões: ${response.statusText}`);
      }
      return response.json();
    })
    .then(data => {
      if (data.content && data.content.length > 0) {
        data.content.forEach(cartao => {
          const div = document.createElement('div');
          div.classList.add('card');
          div.innerHTML = `
            <h3 id="nomeImpresso">${cartao.nomeImpresso}</h3>
            <p id="codigo">Código: ${cartao.codigo}</p>
            <p id="bandeira">Bandeira: ${cartao.bandeira}</p>
            <p id="principal">Principal: ${cartao.principal ? 'Sim' : 'Não'}</p>
            <div class="actions">
              <a href="#" onclick="excluirCartao(${cartao.id})">Excluir</a>
              <p>|</p>
              <a href="alterar-cartao.html?cartaoId=${cartao.id}">Alterar</a>
            </div>
          `;
          userList.appendChild(div);
        });
      } else {
        console.log('Nenhum cartão encontrado.');
      }
    })
    .catch(error => {
      console.error('Erro na requisição:', error);
      alert("Não foi possível carregar os cartões. Tente novamente mais tarde.");
    });
});

// Função para excluir cartão
function excluirCartao(cartaoId) {
  if (!confirm("Tem certeza que deseja excluir este cartão? Esta ação não pode ser desfeita.")) {
    return;
  }

  const deleteUrl = `http://localhost:8080/cliente/cartoes/${cartaoId}`;
  const token = localStorage.getItem('token');

  if (!token) {
    alert("Erro: Token JWT não encontrado! Faça login novamente.");
    window.location.href = "login.html";
    return;
  }

  fetch(deleteUrl, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then(response => {
      if (!response.ok) {
        alert("Este cartão não pode ser excluído porque está vinculado a um pedido.");
        throw new Error(`Erro ao excluir cartão: ${response.statusText}`);
      }
      return response.text();
    })
    .then(() => {
      alert("Cartão excluído com sucesso!");
      location.reload();
    })
    .catch(error => {
      console.error('Erro ao excluir cartão:', error);
      alert("Erro ao excluir cartão. Tente novamente.");
    });
}
