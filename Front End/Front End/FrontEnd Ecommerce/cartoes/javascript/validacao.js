const form = document.getElementById('form');

const principal = document.getElementById('principal');
const nomeImpresso = document.getElementById('nomeImpresso');
const numeroCartao = document.getElementById('numeroCartao');
const codigo = document.getElementById('codigo');
const dataExpiracao = document.getElementById('dataExpiracao');
const bandeira = document.getElementById('bandeira');

function mostrarErro(input, mensagem) {
    const formControl = input.parentElement; 
    formControl.classList.add('erro'); 
    const small = formControl.querySelector('small');
    if (small) {
        small.innerText = mensagem; 
    }
}

function removerErro(input) {
    const formControl = input.parentElement;
    formControl.classList.remove('erro');
    const small = formControl.querySelector('small');
    if (small) {
        small.innerText = '';
    }
}

function validarNome(nomeImpresso) {
    if (nomeImpresso.value.trim() === '' || nomeImpresso.value.trim().length < 3) {
        mostrarErro(nomeImpresso, 'O nome é obrigatório e deve possuir mais de 2 caracteres');
        return false;
    } else {
        removerErro(nomeImpresso);
        return true;
    }
}

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

function validarDataExpiracao(dataExpiracao) {
    if (dataExpiracao.value.trim() === '') {
        mostrarErro(dataExpiracao, 'A data de expiração é obrigatória');
        return false;
    } else {
        removerErro(dataExpiracao);
        return true;
    }
}

form.addEventListener('submit', function (e) {
    e.preventDefault(); 

    const nomeValido = validarNome(nomeImpresso);
    const numeroValido = validarNumeroCartao(numeroCartao);
    const codigoValido = validarCodigo(codigo);
    const bandeiraValida = validarBandeira(bandeira);
    const dataValida = validarDataExpiracao(dataExpiracao);

    if (nomeValido && numeroValido && codigoValido && bandeiraValida && dataValida) {
        console.log('Validações bem-sucedidas');
        form.submit(); 
    } else {
        console.log('Por favor, corrija os erros antes de enviar.');
    }
});
