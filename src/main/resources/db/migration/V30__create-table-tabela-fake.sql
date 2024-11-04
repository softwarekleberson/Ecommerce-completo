CREATE TABLE cartao_fake (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cartao VARCHAR(16) NOT NULL,
    nome_titular VARCHAR(100) NOT NULL,
    limite_credito DECIMAL(10, 2) NOT NULL,  -- Limite total do cartão
    saldo_disponivel DECIMAL(10, 2) NOT NULL,  -- Quanto resta de crédito disponível
    is_ativo BOOLEAN DEFAULT TRUE,  -- Indica se o cartão está ativo
    is_simulado BOOLEAN DEFAULT TRUE  -- Indica que é um cartão simulado
);
