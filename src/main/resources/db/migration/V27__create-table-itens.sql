CREATE TABLE itens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    livros_id BIGINT NOT NULL,
    pedidos_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (livros_id) REFERENCES Livros(id),
    FOREIGN KEY (pedidos_id) REFERENCES pedidos(id) -- Note que aqui o nome é 'pedidos'
);
