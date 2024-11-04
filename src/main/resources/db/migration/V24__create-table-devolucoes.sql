CREATE TABLE devolucoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_devolucao VARCHAR(55) NOT NULL,
    codigo_pedido VARCHAR(55) NOT NULL,
    data_pedido_troca DATE NOT NULL,
    data_conclusao_troca DATE,
    clientes_id BIGINT NOT NULL,
    pedidos_id BIGINT NOT NULL,
    administradores_id BIGINT NOT NULL,
    esperando_devolucao VARCHAR(55) NOT NULL,
    analise_devolucao VARCHAR(55) NOT NULL,
    CONSTRAINT fk_devolucoes_clientes FOREIGN KEY (clientes_id) REFERENCES clientes(id),
    CONSTRAINT fk_devolucoes_pedidos FOREIGN KEY (pedidos_id) REFERENCES pedidos(id),
    CONSTRAINT fk_devolucoes_administradores FOREIGN KEY (administradores_id) REFERENCES administradores(id)
);
