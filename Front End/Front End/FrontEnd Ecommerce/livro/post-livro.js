document.getElementById('form').addEventListener('submit', function(event) { 
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);
    const token = localStorage.getItem('token'); 

    const data = {
        idPrecificacao: formData.get('idPrecificacao'),
        data: formData.get('data'),
        preco: parseFloat(formData.get('preco')),
        titulo: formData.get('titulo'),
        isbn: formData.get('isbn'),
        paginas: parseInt(formData.get('paginas')),
        sinopse: formData.get('sinopse'),
        codigoBarra: formData.get('codigoBarra'),
        dimensoes: {
            altura: parseFloat(formData.get('altura')),
            largura: parseFloat(formData.get('largura')),
            peso: parseFloat(formData.get('peso')),
            profundidade: parseFloat(formData.get('profundidade'))
        },
        editora: formData.get('editora'),
        edicao: formData.get('edicao'),
        imagem: [
            { url: formData.get('imagem1') },
            { url: formData.get('imagem2') },
            { url: formData.get('imagem3') }
        ].filter(img => img.url),
        autor: [{ nome: formData.get('autor') }],
        categoria: [{ nome: formData.get('categoria') }]
    };

    fetch('http://localhost:8080/admin/livros', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` 
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        console.log('Success:', result);
        window.location.href = "home-adm.html"
        form.reset();  
    })
    .catch(error => {
        alert('Erro ao cadastrar novo livro');
    });
});
