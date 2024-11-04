CREATE TABLE entregas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  principal TINYINT(1) NOT NULL DEFAULT 1,
  receptor VARCHAR (255) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero VARCHAR(255) NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  cep VARCHAR(9) NOT NULL,
  observacao VARCHAR(1000),
  frase_entrega VARCHAR(1000) NOT NULL,
  tipo_logradouro VARCHAR(255) NOT NULL,
  tipo_residencia VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255) NOT NULL,
  pais VARCHAR(255) NOT NULL,
  clientes_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (clientes_id) REFERENCES clientes (id)
);



