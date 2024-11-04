ALTER TABLE livros
ADD COLUMN precificacao_id INT NOT NULL,
ADD FOREIGN KEY (precificacao_id) REFERENCES precificacoes(id);
