CREATE TABLE cupons (
	id VARCHAR(36) PRIMARY KEY,          -- UUID como String
	tipo_cupom VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status TINYINT(1) NOT NULL,
    clientes_id BIGINT NOT NULL,
    CONSTRAINT cupons_clientes_fk FOREIGN KEY (clientes_id) REFERENCES clientes (id)
);
