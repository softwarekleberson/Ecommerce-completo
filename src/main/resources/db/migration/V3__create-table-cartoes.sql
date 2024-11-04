CREATE TABLE cartoes (
  id BIGINT NOT NULL AUTO_INCREMENT,
  principal TINYINT(1) NOT NULL DEFAULT 1,
  nome_impresso VARCHAR(255) NOT NULL,
  codigo VARCHAR(4) NOT NULL,
  numero_cartao VARCHAR(19) NOT NULL,
  data_expiracao DATE NOT NULL,
  bandeira VARCHAR(50) NOT NULL,
  clientes_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT clientes_id_fk FOREIGN KEY (clientes_id) REFERENCES clientes (id),
  CONSTRAINT chk_codigo CHECK (LENGTH(codigo) = 3 OR LENGTH(codigo) = 4 AND codigo REGEXP '^[0-9]+$'),
  CONSTRAINT chk_numero_cartao CHECK (LENGTH(numero_cartao) BETWEEN 13 AND 19 AND numero_cartao REGEXP '^[0-9]+$')
);
