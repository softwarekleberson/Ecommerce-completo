document.getElementById('cupomForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData);

    try {
        const response = await fetch('http://localhost:8080/administrador/cupons', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error(`Erro: ${response.statusText}`);
        }

        const result = await response.json();
        console.log('Sucesso:', result);
        event.target.reset();
        
    } catch (error) {
        console.error('Erro:', error);
    }
});