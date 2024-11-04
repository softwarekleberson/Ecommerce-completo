CREATE TABLE statuslivro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    justificativa VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    livro_id BIGINT NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
