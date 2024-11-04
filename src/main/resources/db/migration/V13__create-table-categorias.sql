CREATE TABLE categorias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    categoria VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE livro_categoria (
    livro_id BIGINT NOT NULL,
    categoria_id BIGINT NOT NULL,
    PRIMARY KEY (livro_id, categoria_id),
    FOREIGN KEY (livro_id) REFERENCES livros(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);
