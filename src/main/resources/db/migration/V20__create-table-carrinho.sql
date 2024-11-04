CREATE TABLE carrinhos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_criacao DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
