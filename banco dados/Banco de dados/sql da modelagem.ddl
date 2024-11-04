-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2024-05-03 15:12:39 BRT
--   site:      Oracle Database 21c
--   tipo:      Oracle Database 21c

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE administradores (
    id         INTEGER NOT NULL,
    nome       VARCHAR2(255) NOT NULL,
    email      VARCHAR2(255) NOT NULL,
    senha      VARCHAR2(500) NOT NULL,
    estoque_id INTEGER NOT NULL
);

CREATE UNIQUE INDEX administradores__idx ON
    administradores (
        estoque_id
    ASC );

ALTER TABLE administradores ADD CONSTRAINT administradores_pk PRIMARY KEY ( id );

CREATE TABLE autores (
    id   INTEGER NOT NULL,
    nome VARCHAR2(255) NOT NULL
);

ALTER TABLE autores ADD CONSTRAINT autores_pk PRIMARY KEY ( id );

CREATE TABLE cartoes (
    id           INTEGER NOT NULL,
    principal    NUMBER,
    nome         VARCHAR2(255) NOT NULL,
    codigo       VARCHAR2(255) NOT NULL,
    numero       VARCHAR2(255) NOT NULL,
    cliente_id   INTEGER,
    bandeira     VARCHAR2(255) NOT NULL,
    pagamento_id INTEGER
);

ALTER TABLE cartoes ADD CONSTRAINT cartoes_pk PRIMARY KEY ( id );

CREATE TABLE categorias (
    id        INTEGER NOT NULL,
    categoria VARCHAR2(255) NOT NULL
);

ALTER TABLE categorias ADD CONSTRAINT categorias_pk PRIMARY KEY ( id );

CREATE TABLE clientes (
    id        INTEGER NOT NULL,
    nome      VARCHAR2(255) NOT NULL,
    genero    VARCHAR2(255) NOT NULL,
    cpf       VARCHAR2(11) NOT NULL,
    nacimento DATE NOT NULL,
    senha     VARCHAR2(255) NOT NULL,
    status    NUMBER NOT NULL,
    ddd       VARCHAR2(2) NOT NULL,
    telefone  VARCHAR2(9) NOT NULL,
    tipo      VARCHAR2(255) NOT NULL
);

ALTER TABLE clientes ADD CONSTRAINT cliente_pk PRIMARY KEY ( id );

CREATE TABLE cobrancas (
    id         INTEGER NOT NULL,
    status     NUMBER NOT NULL,
    cliente_id INTEGER NOT NULL
);

ALTER TABLE cobrancas ADD CONSTRAINT cobrancas_pk PRIMARY KEY ( id );

CREATE TABLE cupom (
    id                 INTEGER NOT NULL,
    status             NUMBER NOT NULL,
    valor              NUMBER(10, 2) NOT NULL,
    tipo               VARCHAR2(255) NOT NULL,
    administradores_id INTEGER,
    clientes_id        INTEGER,
    pagamento_id       INTEGER
);

ALTER TABLE cupom ADD CONSTRAINT cupom_pk PRIMARY KEY ( id );

CREATE TABLE devolucao (
    id        INTEGER NOT NULL,
    pedido_id INTEGER NOT NULL
);

ALTER TABLE devolucao ADD CONSTRAINT devolucao_pk PRIMARY KEY ( id );

CREATE TABLE endereco (
    id              INTEGER NOT NULL,
    logradouro      VARCHAR2(255) NOT NULL,
    numero          VARCHAR2(255) NOT NULL,
    bairro          VARCHAR2(255) NOT NULL,
    cep             VARCHAR2(8) NOT NULL,
    observacao      VARCHAR2(1000),
    cidade          VARCHAR2(255) NOT NULL,
    estado          VARCHAR2(255) NOT NULL,
    pais            VARCHAR2(255) NOT NULL,
    tipo_logradouo  VARCHAR2(255) NOT NULL,
    tipo_residencia VARCHAR2(255) NOT NULL
);

ALTER TABLE endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( id );

CREATE TABLE entregas (
    id            INTEGER NOT NULL,
    frase_entrega VARCHAR2(255) NOT NULL,
    status        NUMBER NOT NULL,
    cliente_id    INTEGER NOT NULL
);

ALTER TABLE entregas ADD CONSTRAINT entrega_pk PRIMARY KEY ( id );

ALTER TABLE entregas ADD CONSTRAINT entrega_pkv1 UNIQUE ( cliente_id );

CREATE TABLE estoque (
    id           INTEGER NOT NULL,
    quantidade   INTEGER NOT NULL,
    valor_custo  NUMBER(10, 2) NOT NULL,
    data_entrada DATE NOT NULL,
    livros_id    INTEGER NOT NULL,
    fornecedor   VARCHAR2(255) NOT NULL
);

CREATE UNIQUE INDEX estoque__idx ON
    estoque (
        livros_id
    ASC );

ALTER TABLE estoque ADD CONSTRAINT estoque_pk PRIMARY KEY ( id );

CREATE TABLE imagens (
    id        INTEGER NOT NULL,
    url       VARCHAR2(500) NOT NULL,
    livros_id INTEGER NOT NULL
);

ALTER TABLE imagens ADD CONSTRAINT imagens_pk PRIMARY KEY ( id );

CREATE TABLE itens (
    id             INTEGER NOT NULL,
    quantidade     INTEGER NOT NULL,
    valor_unitario NUMBER(10, 2) NOT NULL,
    pedido_id      INTEGER NOT NULL
);

ALTER TABLE itens ADD CONSTRAINT itens_pk PRIMARY KEY ( id );

CREATE TABLE livro_autores (
    autores_id INTEGER NOT NULL,
    livros_id  INTEGER NOT NULL
);

ALTER TABLE livro_autores ADD CONSTRAINT relation_8_pk PRIMARY KEY ( autores_id,
                                                                     livros_id );

CREATE TABLE livro_categoria (
    livros_id     INTEGER NOT NULL,
    categorias_id INTEGER NOT NULL
);

ALTER TABLE livro_categoria ADD CONSTRAINT relation_9_pk PRIMARY KEY ( livros_id,
                                                                       categorias_id );

CREATE TABLE livros (
    id               INTEGER NOT NULL,
    ano              DATE NOT NULL,
    titulo           VARCHAR2(255) NOT NULL,
    isbn             VARCHAR2(13) NOT NULL,
    paginas          INTEGER NOT NULL,
    sinopse          CLOB NOT NULL,
    altura           INTEGER NOT NULL,
    largura          INTEGER NOT NULL,
    editora          VARCHAR2(255) NOT NULL,
    edicao           VARCHAR2(255) NOT NULL,
    valor_custo      NUMBER(10, 2) NOT NULL,
    precificacoes_id INTEGER NOT NULL,
    estoque_id       INTEGER NOT NULL,
    itens_id         INTEGER NOT NULL
);

CREATE UNIQUE INDEX livros__idx ON
    livros (
        estoque_id, -- Adicione uma coluna para evitar índice vazio
        itens_id
    ASC );

ALTER TABLE livros ADD CONSTRAINT livros_pk PRIMARY KEY ( id );

CREATE TABLE pagamento (
    id             INTEGER NOT NULL,
    valor_total    NUMBER(10, 2) NOT NULL,
    data_pagamento DATE NOT NULL,
    status         VARCHAR2(255) NOT NULL
);

ALTER TABLE pagamento ADD CONSTRAINT pagamento_pk PRIMARY KEY ( id );

CREATE TABLE pedido (
    id                 INTEGER NOT NULL,
    data_pedido        DATE NOT NULL,
    valor_total        NUMBER(10, 2) NOT NULL,
    pagamento_id       INTEGER NOT NULL,
    administradores_id INTEGER NOT NULL
);

CREATE UNIQUE INDEX pedido__idx ON
    pedido (
        pagamento_id
    ASC );

ALTER TABLE pedido ADD CONSTRAINT pedido_pk PRIMARY KEY ( id );

CREATE TABLE precificacoes (
    id           INTEGER NOT NULL,
    precificacao NUMBER(10, 2) NOT NULL
);

ALTER TABLE precificacoes ADD CONSTRAINT precificacoes_pk PRIMARY KEY ( id );

CREATE TABLE troca (
    id        INTEGER NOT NULL,
    pedido_id INTEGER NOT NULL
);

ALTER TABLE troca ADD CONSTRAINT troca_pk PRIMARY KEY ( id );

ALTER TABLE administradores
    ADD CONSTRAINT administradores_estoque_fk FOREIGN KEY ( estoque_id )
        REFERENCES estoque ( id );

ALTER TABLE cartoes
    ADD CONSTRAINT cartoes_cliente_fk FOREIGN KEY ( cliente_id )
        REFERENCES clientes ( id );

ALTER TABLE cartoes
    ADD CONSTRAINT cartoes_pagamento_fk FOREIGN KEY ( pagamento_id )
        REFERENCES pagamento ( id );

ALTER TABLE cobrancas
    ADD CONSTRAINT cobrancas_cliente_fk FOREIGN KEY ( cliente_id )
        REFERENCES clientes ( id );

ALTER TABLE cobrancas
    ADD CONSTRAINT cobrancas_endereco_fk FOREIGN KEY ( id )
        REFERENCES endereco ( id );

ALTER TABLE cupom
    ADD CONSTRAINT cupom_administradores_fk FOREIGN KEY ( administradores_id )
        REFERENCES administradores ( id );

ALTER TABLE cupom
    ADD CONSTRAINT cupom_clientes_fk FOREIGN KEY ( clientes_id )
        REFERENCES clientes ( id );

ALTER TABLE cupom
    ADD CONSTRAINT cupom_pagamento_fk FOREIGN KEY ( pagamento_id )
        REFERENCES pagamento ( id );

ALTER TABLE devolucao
    ADD CONSTRAINT devolucao_pedido_fk FOREIGN KEY ( pedido_id )
        REFERENCES pedido ( id );

ALTER TABLE entregas
    ADD CONSTRAINT entrega_cliente_fk FOREIGN KEY ( cliente_id )
        REFERENCES clientes ( id );

ALTER TABLE entregas
    ADD CONSTRAINT entrega_endereco_fk FOREIGN KEY ( id )
        REFERENCES endereco ( id );

ALTER TABLE estoque
    ADD CONSTRAINT estoque_livros_fk FOREIGN KEY ( livros_id )
        REFERENCES livros ( id );

ALTER TABLE imagens
    ADD CONSTRAINT imagens_livros_fk FOREIGN KEY ( livros_id )
        REFERENCES livros ( id );

ALTER TABLE itens
    ADD CONSTRAINT itens_pedido_fk FOREIGN KEY ( pedido_id )
        REFERENCES pedido ( id );

ALTER TABLE livros
    ADD CONSTRAINT livros_estoque_fk FOREIGN KEY ( estoque_id )
        REFERENCES estoque ( id );

ALTER TABLE livros
    ADD CONSTRAINT livros_itens_fk FOREIGN KEY ( itens_id )
        REFERENCES itens ( id );

ALTER TABLE livros
    ADD CONSTRAINT livros_precificacoes_fk FOREIGN KEY ( precificacoes_id )
        REFERENCES precificacoes ( id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_administradores_fk FOREIGN KEY ( administradores_id )
        REFERENCES administradores ( id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_pagamento_fk FOREIGN KEY ( pagamento_id )
        REFERENCES pagamento ( id );

ALTER TABLE livro_autores
    ADD CONSTRAINT relation_8_autores_fk FOREIGN KEY ( autores_id )
        REFERENCES autores ( id );

ALTER TABLE livro_autores
    ADD CONSTRAINT relation_8_livros_fk FOREIGN KEY ( livros_id )
        REFERENCES livros ( id );

ALTER TABLE livro_categoria
    ADD CONSTRAINT relation_9_categorias_fk FOREIGN KEY ( categorias_id )
        REFERENCES categorias ( id );

ALTER TABLE livro_categoria
    ADD CONSTRAINT relation_9_livros_fk FOREIGN KEY ( livros_id )
        REFERENCES livros ( id );

ALTER TABLE troca
    ADD CONSTRAINT troca_pedido_fk FOREIGN KEY ( pedido_id )
        REFERENCES pedido ( id );

--  ERROR: No Discriminator Column found in Arc FKArc_1 - constraint trigger for Arc cannot be generated 

--  ERROR: No Discriminator Column found in Arc FKArc_1 - constraint trigger for Arc cannot be generated

-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            20
-- CREATE INDEX                             5
-- ALTER TABLE                             45
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
