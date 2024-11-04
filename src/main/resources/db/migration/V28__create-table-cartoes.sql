ALTER TABLE cartoes 
ADD COLUMN pagamento_id VARCHAR(36) NULL,
ADD CONSTRAINT fk_pagamento_cartao
FOREIGN KEY (pagamento_id)
REFERENCES pagamentos(id);
