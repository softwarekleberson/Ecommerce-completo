CREATE TABLE livros (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ativo TINYINT NOT NULL DEFAULT 0,
    data DATE NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    isbn VARCHAR(17) NOT NULL,
    paginas INT NOT NULL,
    sinopse TEXT NOT NULL,
    codigo_barra VARCHAR(13) NOT NULL,
    altura DOUBLE NOT NULL,
    largura DOUBLE NOT NULL,
    peso DOUBLE NOT NULL,
    profundidade DOUBLE NOT NULL,
    editora VARCHAR(100) NOT NULL,
    edicao VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
