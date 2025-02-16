const url = 'http://localhost:8080/cliente/cartoes';

const token = localStorage.getItem('token');

if (token) {
  fetch(url, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then(response => response.json())
    .then(data => {
      if (data.content && data.content.length > 0) {
        const userList = document.getElementById('userList');

        userList.innerHTML = '';

        data.content.forEach(cartao => {
          const div = document.createElement('div');
          div.classList.add('card');
          div.innerHTML = `
            <h4 id="nomeImpresso">${cartao.nomeImpresso}</h4>
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
    });
} else {
  console.error('Token não encontrado no localStorage');
}

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
        throw new Error(`Erro ao excluir cartão: ${response.status} - ${response.statusText}`);
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
