// Captura o form
const form = document.getElementById('form');

// Captura os campos
const principal = document.getElementById('principal');
const nomeImpresso = document.getElementById('nomeImpresso');
const numeroCartao = document.getElementById('numeroCartao');
const codigo = document.getElementById('codigo');
const dataExpiracao = document.getElementById('dataExpiracao');
const bandeira = document.getElementById('bandeira');

// Função para exibir erro
function mostrarErro(input, mensagem) {
    const formControl = input.parentElement; // Pega a div do campo
    formControl.classList.add('erro'); // Adiciona a classe de erro
    const small = formControl.querySelector('small');
    if (small) {
        small.innerText = mensagem; // Exibe a mensagem de erro
    }
}

// Função para remover o erro
function removerErro(input) {
    const formControl = input.parentElement;
    formControl.classList.remove('erro');
    const small = formControl.querySelector('small');
    if (small) {
        small.innerText = '';
    }
}

// Valida o nome impresso
function validarNome(nomeImpresso) {
    if (nomeImpresso.value.trim() === '' || nomeImpresso.value.trim().length < 3) {
        mostrarErro(nomeImpresso, 'O nome é obrigatório e deve possuir mais de 2 caracteres');
        return false;
    } else {
        removerErro(nomeImpresso);
        return true;
    }
}

// Valida o número do cartão (16 dígitos)
function validarNumeroCartao(numeroCartao) {
    const regex = /^\d{16}$/;
    if (!regex.test(numeroCartao.value.trim())) {
        mostrarErro(numeroCartao, 'O número do cartão deve ter 16 dígitos');
        return false;
    } else {
        removerErro(numeroCartao);
        return true;
    }
}

// Valida o código de segurança (3 dígitos)
function validarCodigo(codigo) {
    const regex = /^\d{3}$/;
    if (!regex.test(codigo.value.trim())) {
        mostrarErro(codigo, 'O código do cartão deve ter 3 dígitos');
        return false;
    } else {
        removerErro(codigo);
        return true;
    }
}

// Valida a bandeira do cartão com base no ENUM (MAIÚSCULO)
function validarBandeira(bandeira) {
    const opcoesValidas = ['MASTERCARD', 'VISA', 'ELO'];
    const valor = bandeira.value.trim();

    if (!opcoesValidas.includes(valor)) {
        mostrarErro(bandeira, 'Opção de bandeira inválida');
        return false;
    } else {
        removerErro(bandeira);
        return true;
    }
}

// Valida a data de expiração (campo obrigatório)
function validarDataExpiracao(dataExpiracao) {
    if (dataExpiracao.value.trim() === '') {
        mostrarErro(dataExpiracao, 'A data de expiração é obrigatória');
        return false;
    } else {
        removerErro(dataExpiracao);
        return true;
    }
}

// Evento de envio do formulário
form.addEventListener('submit', function (e) {
    e.preventDefault(); // Impede o envio automático para validar primeiro

    const nomeValido = validarNome(nomeImpresso);
    const numeroValido = validarNumeroCartao(numeroCartao);
    const codigoValido = validarCodigo(codigo);
    const bandeiraValida = validarBandeira(bandeira);
    const dataValida = validarDataExpiracao(dataExpiracao);

    // Se todas as validações forem verdadeiras, o formulário é enviado
    if (nomeValido && numeroValido && codigoValido && bandeiraValida && dataValida) {
        console.log('Validações bem-sucedidas');
        form.submit(); // Aqui pode fazer um fetch ou enviar o form direto
    } else {
        console.log('Por favor, corrija os erros antes de enviar.');
    }
});
