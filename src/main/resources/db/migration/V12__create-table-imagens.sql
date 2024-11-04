CREATE TABLE imagens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    livro_id BIGINT NOT NULL,
    url VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
