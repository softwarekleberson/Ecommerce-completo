ALTER TABLE cupons 
ADD COLUMN pagamento_id VARCHAR(36) NULL,
ADD CONSTRAINT fk_pagamento_cupom
FOREIGN KEY (pagamento_id)
REFERENCES pagamentos(id);
