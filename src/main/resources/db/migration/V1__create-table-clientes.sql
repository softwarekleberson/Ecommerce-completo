CREATE TABLE clientes (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  nascimento DATE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  genero VARCHAR(20) NOT NULL,
  ddd VARCHAR(3) NOT NULL,
  telefone VARCHAR(9) NOT NULL,
  tipo_Telefone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);