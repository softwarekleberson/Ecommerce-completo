CREATE TABLE pagamentos (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    data_pagamento TIMESTAMP NULL,
    entrega_id BIGINT NOT NULL,
    cobranca_id BIGINT NOT NULL,
    valor_total DECIMAL(15, 2) NULL,
    status_compra VARCHAR(20) NULL,
    CONSTRAINT fk_entrega
        FOREIGN KEY (entrega_id) REFERENCES entregas(id),
    CONSTRAINT fk_cobranca
        FOREIGN KEY (cobranca_id) REFERENCES cobrancas(id)
);
