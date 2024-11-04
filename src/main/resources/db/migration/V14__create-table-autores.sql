CREATE TABLE autores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    autor VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE livro_autor (
    livro_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (livro_id, autor_id),
    FOREIGN KEY (livro_id) REFERENCES livros(id),
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);
