CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

CREATE TABLE administradores (
    id BIGINT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES user_entity(id)
);

ALTER TABLE clientes 
ADD CONSTRAINT fk_clientes_user 
FOREIGN KEY (id) REFERENCES user_entity(id);